package io.github.funch.fcfs.ticket.repo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.time.LocalDateTime

private const val PK_PREFIX = "subject#"
private const val PK_SUFFIX = "#ticket"
private const val SK_PREFIX = "client#"

@DynamoDBTable(tableName = "fcfs-ticket")
data class TicketDao(
        @DynamoDBHashKey(attributeName = "PK")
        var pk: String,
        @DynamoDBRangeKey(attributeName = "SK")
        var sk: String,
        @DynamoDBAttribute(attributeName = "createdAt")
        var createdAt: LocalDateTime
) {
}