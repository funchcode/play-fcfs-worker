package io.github.funch.fcfs.ticket.repo

import io.github.funch.fcfs.common.PrivateConfig
import io.github.funch.fcfs.subject.Subject
import io.github.funch.fcfs.ticket.Ticket
import io.github.funch.fcfs.ticket.TicketRepository

class TicketDynamoRepository : TicketRepository {

    override fun findBySubjectIdAndClientId(subjectId: String, clientId: String): Ticket? {
        TODO("Not yet implemented")
        PrivateConfig.getAwsCredentialAccessKey()
    }

    override fun save(ticket: Ticket): Ticket {
        PrivateConfig.getAwsCredentialSecretKey()
        return Ticket(Subject(), "")
    }

}