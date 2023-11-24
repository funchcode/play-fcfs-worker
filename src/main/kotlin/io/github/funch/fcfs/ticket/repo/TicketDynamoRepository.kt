package io.github.funch.fcfs.ticket.repo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.ticket.Ticket
import io.github.funch.fcfs.ticket.TicketRepository
import java.time.LocalDateTime
import java.util.stream.Collectors
import kotlin.collections.HashMap


class TicketDynamoRepository(
        private val dynamoDBMapper: DynamoDBMapper,
        private val subjectRepository: SubjectRepository
) : TicketRepository {

    override fun findAllBySubjectId(subjectId: String): List<Ticket> {
        val eav: MutableMap<String, AttributeValue> = HashMap()
        eav[":val1"] = AttributeValue().withS(TicketDao.toPk(subjectId))

        val queryExpression = DynamoDBQueryExpression<TicketDao>()
                .withKeyConditionExpression("PK = :val1").withExpressionAttributeValues(eav)
        val tickets: List<TicketDao> = dynamoDBMapper.query(TicketDao::class.java, queryExpression)
        return tickets.stream().map { it.toDomain(subjectRepository.findById(it.getSubjectId()).orElse(null)) }.collect(Collectors.toList())
    }

    override fun findBySubjectIdAndClientId(subjectId: String, clientId: String): Ticket? {
        try {
            val dao: TicketDao = dynamoDBMapper.load(TicketDao::class.java, TicketDao.toPk(subjectId), TicketDao.toSk(clientId))
            return dao.toDomain(subjectRepository.findById(dao.getSubjectId()).orElseThrow())
        } catch (e: Exception) {
            return null
        }
    }

    override fun save(ticket: Ticket) {
        val dao = TicketDao(ticket)
        if (findBySubjectIdAndClientId(ticket.subject.getId(), ticket.clientId) == null) {
            dao.setCreatedAt(LocalDateTime.now())
        }
        dynamoDBMapper.save(dao)
    }

}