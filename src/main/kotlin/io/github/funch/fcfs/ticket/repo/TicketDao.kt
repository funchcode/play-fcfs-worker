package io.github.funch.fcfs.ticket.repo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import io.github.funch.fcfs.subject.Subject
import io.github.funch.fcfs.ticket.Ticket
import java.time.LocalDateTime

@DynamoDBTable(tableName = "fcfs-ticket")
data class TicketDao(
        @DynamoDBHashKey(attributeName = "PK")
        val pk: String,
        @DynamoDBRangeKey(attributeName = "SK")
        val sk: String,
        @DynamoDBAttribute(attributeName = "createdAt")
        var createdAt: LocalDateTime?
) {

        companion object {
                val PK_PREFIX = "subject#";
                val PK_SUFFIX = "#ticket";
                val SK_PREFIX = "client#";

                fun toPk(subjectId: String): String {
                        return String.format("%s%s%s", PK_PREFIX, subjectId, PK_SUFFIX)
                }

                fun toSk(clientId: String): String {
                        return String.format("%s%s", SK_PREFIX, clientId)
                }

        }

        constructor(ticket: Ticket): this(toPk(ticket.subject.getId()), toSk(ticket.clientId), null)

        fun getSubjectId(): String {
                var subjectId = pk.replace(PK_PREFIX, "")
                subjectId = subjectId.replace(PK_SUFFIX, "")
                return subjectId
        }

        fun getClientId(): String {
                return this.getClientId().replace(SK_PREFIX, "")
        }

        fun toDomain(subject: Subject): Ticket {
                return Ticket(subject, getClientId())
        }

        fun setCreatedAt(createdAt: LocalDateTime) {
                this.createdAt = createdAt;
        }

}