package io.github.funch.fcfs.subject

import io.github.funch.fcfs.ticket.Ticket
import java.util.*

class SubjectTickets(
        private val subject: Subject,
        private val tickets: Set<Ticket>
) {

    /**
     * SUBJECT 발급 가능 상태 확인
     */
    fun checkIssueable() {
        subject.checkIssueable()
        if (quantityLeft() <= 0) {
            // 티켓 여분이 없음
            throw TicketIssueException()
        }
    }

    /**
     * 티켓 남은 여분
     */
    private fun quantityLeft(): Int {
        return subject.limitedQuantityOf - tickets.size
    }

    private fun getTicketByClientId(clientId: String): Optional<Ticket> {
        for (ticket in tickets) {
            if (clientId == ticket.clientId) {
                return Optional.of(ticket)
            }
        }
        return Optional.empty()
    }

    fun issueTicket(clientId: String): Ticket {
        checkIssueable()
        if (getTicketByClientId(clientId).isPresent) {
            throw TicketIssueException()
        }
        val ticket: Ticket = Ticket.newInstance(subject, clientId)
        tickets.plus(ticket)
        return ticket
    }

}