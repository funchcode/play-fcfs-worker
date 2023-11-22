package io.github.funch.fcfs

import com.google.gson.Gson
import io.github.funch.fcfs.mq.TicketMessage
import io.github.funch.fcfs.subject.SubjectRepository
import software.amazon.awssdk.services.sqs.model.Message

class Processor {

    var subjectRepository: SubjectRepository? = null

    fun processMessage(message: Message) {
        try {
            val ticketMessage = Gson().fromJson(message.body(), TicketMessage::class.java)
            println(ticketMessage.clientId)
            println(ticketMessage.subjectId)
            //        val subjectId = ""
            //        val clientId = ""
            //        val subject = subjectRepository?.findById(subjectId)
            //        Ticketer().ticketing(subject!!, clientId)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}