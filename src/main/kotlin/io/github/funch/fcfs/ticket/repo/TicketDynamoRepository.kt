package io.github.funch.fcfs.ticket.repo

import io.github.funch.fcfs.common.PrivateConfig
import io.github.funch.fcfs.subject.Subject
import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.ticket.Ticket
import io.github.funch.fcfs.ticket.TicketRepository

class TicketDynamoRepository : TicketRepository {

    override fun findAllBySubjectId(subjectId: String): List<Ticket> {
        TODO("Not yet implemented")
    }

    override fun findBySubjectIdAndClientId(subjectId: String, clientId: String): Ticket? {
        TODO("Not yet implemented")
        PrivateConfig.getAwsCredentialAccessKey()
    }

    override fun save(ticket: Ticket) {
        PrivateConfig.getAwsCredentialSecretKey()
        // todo: save
    }

}