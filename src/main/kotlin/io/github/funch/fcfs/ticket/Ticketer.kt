package io.github.funch.fcfs.ticket

import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.subject.SubjectTickets

class Ticketer(
        private val subjectRepository: SubjectRepository,
        private val ticketRepository: TicketRepository
) {

    fun ticketing(subjectId: String, clientId: String) {
        val subject = subjectRepository.findById(subjectId)
        val tickets = ticketRepository.findAllBySubjectId(subjectId)
        val subjectTickets = SubjectTickets(subject, tickets.toSet())
        val ticket = subjectTickets.issueTicket(clientId)
        ticketRepository.save(ticket)
    }

}