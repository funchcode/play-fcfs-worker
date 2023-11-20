package io.github.funch.fcfs

import io.github.funch.fcfs.mq.TicketMessage
import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.ticket.Ticketer

class Processor {

    var subjectRepository: SubjectRepository? = null

    fun processMessage(ticketMessage: TicketMessage) {
        // todo message to subjectId, clientId
        val subjectId = ""
        val clientId = ""
        val subject = subjectRepository?.findById(subjectId)
        Ticketer().ticketing(subject!!, clientId)
    }

}