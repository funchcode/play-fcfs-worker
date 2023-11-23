package io.github.funch.fcfs.ticket

interface TicketRepository {

    fun findAllBySubjectId(subjectId: String): List<Ticket>
    fun findBySubjectIdAndClientId(subjectId: String, clientId: String): Ticket?
    fun save(ticket: Ticket)

}