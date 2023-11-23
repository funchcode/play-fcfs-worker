package io.github.funch.fcfs

import com.google.gson.Gson
import io.github.funch.fcfs.mq.TicketMessage
import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.subject.repo.SubjectDynamoRepository
import io.github.funch.fcfs.ticket.TicketRepository
import io.github.funch.fcfs.ticket.Ticketer
import io.github.funch.fcfs.ticket.repo.TicketDynamoRepository
import software.amazon.awssdk.services.sqs.model.Message

class Processor {

    private val ticketRepository: TicketRepository = TicketDynamoRepository()
    private val subjectRepository: SubjectRepository = SubjectDynamoRepository()

    fun processMessage(message: Message) {
        try {
            val ticketMessage = Gson().fromJson(message.body(), TicketMessage::class.java)
            println(ticketMessage.clientId)
            println(ticketMessage.subjectId)
            // 등록 작업 진행 (Mutex)
            Ticketer(subjectRepository, ticketRepository).ticketing(ticketMessage.subjectId, ticketMessage.clientId)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}