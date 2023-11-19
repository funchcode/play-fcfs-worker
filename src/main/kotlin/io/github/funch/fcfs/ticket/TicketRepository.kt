package io.github.funch.fcfs.ticket

interface TicketRepository {

    fun findBySubjectIdAndClientId(subjectId: String, clientId: String): Ticket?
    fun save(ticket: Ticket): Ticket

}