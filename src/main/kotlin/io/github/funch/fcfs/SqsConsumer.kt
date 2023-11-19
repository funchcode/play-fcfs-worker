package io.github.funch.fcfs

import io.github.funch.fcfs.mq.Message

class SqsConsumer {

    // sqs consumer polling coroutine 실행
    fun start() {
        // GET Message (SQS)
        val message = Message()
        try {
            Processor().processMessage(message)
        } catch (e: Exception) {
            TODO("DELETE Message SQS")
        }
    }

}