package io.github.funch.fcfs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.future.await
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.Message
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest
import java.lang.Thread.currentThread

class SqsConsumer {

    private var sqs: SqsAsyncClient = SqsAsyncClient.builder()
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("", "")))
            .region(Region.AP_NORTHEAST_2)
            .build()

    fun start() = runBlocking {
        val messageChannel = Channel<Message>()
        repeat(10) {
            launchProcessor(messageChannel)
        }
        launchMessageReceiver(messageChannel)
    }

    fun CoroutineScope.launchProcessor(channel: ReceiveChannel<Message>) = launch {
        while (isActive) {
            println("${currentThread().name} processor")
            for (message in channel) {
                println("${message.body()}")
                sqs.deleteMessage {
                    it.queueUrl("")
                    it.receiptHandle(message.receiptHandle())
                }
            }
        }
    }

    fun CoroutineScope.launchMessageReceiver(channel: SendChannel<Message>) = launch {
        while (isActive) {
            println("${currentThread().name} receiver")
            val receiverRequest = ReceiveMessageRequest.builder()
                    .queueUrl("")
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