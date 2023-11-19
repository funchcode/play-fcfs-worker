package io.github.funch.fcfs.ticket

import io.github.funch.fcfs.subject.Subject

class Ticketer {

    var ticketRepository: TicketRepository? = null

    fun ticketing(subject: Subject, clientId: String) {
        // 이미 등록된 요청인지 확인
        // 등록 작업 진행 (Mutex)
        val ticket = Ticket(subject, clientId)
        ticket.checkIssueable()
        ticketRepository?.save(ticket)
    }

}