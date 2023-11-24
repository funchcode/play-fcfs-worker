package io.github.funch.fcfs

import io.github.funch.fcfs.common.Configs
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.future.await
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import java.lang.Thread.currentThread
import kotlin.coroutines.CoroutineContext

class SqsConsumer(private val sqs: SqsAsyncClient): CoroutineScope {

    private val processor = Processor()
    private val supervisorJob = SupervisorJob()
    private val mutex = Mutex()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + supervisorJob

    fun start() = runBlocking {
        val messageChannel = Channel<Message>()
        repeat(10) {
            launchProcessor(messageChannel)
        }
        launchMessageReceiver(messageChannel)
    }

    private fun CoroutineScope.launchProcessor(channel: ReceiveChannel<Message>) = launch {
        while (isActive) {
            println("${currentThread().name} processor")
            for (message in channel) {
                try {
                    mutex.withLock {
                        processor.processMessage(message)
                    }
                    sqs.deleteMessage {
                        it.queueUrl(Configs.getAwsSqsUrl())
                        it.receiptHandle(message.receiptHandle())
                    }.await()
                } catch (ex: IllegalAccessException) {
                    println(ex.printStackTrace())
                }
            }
        }
    }

    private fun CoroutineScope.launchMessageReceiver(channel: SendChannel<Message>) = launch {
        while (isActive) {
            println("${currentThread().name} receiver")
            val receiverRequest = ReceiveMessageRequest.builder()
                    .queueUrl(Configs.getAwsSqsUrl())
                    .waitTimeSeconds(5)
                    .maxNumberOfMessages(10)
                    .build()

            val messages = sqs.receiveMessage(receiverRequest).await().messages()
            println("${messages.size} messages")

            messages.forEach {
                channel.send(it)
            }
        }
    }

}