package io.github.funch.fcfs.subject.repo

import com.amazonaws.services.dynamodbv2.datamodeling.*
import io.github.funch.fcfs.common.DynamoDBLocalDateTimeConverter
import io.github.funch.fcfs.subject.DynamoDBStatusConverter
import io.github.funch.fcfs.subject.Status
import io.github.funch.fcfs.subject.Subject
import java.time.LocalDateTime

@DynamoDBTable(tableName = "fcfs-ticket")
class SubjectDao(
        @DynamoDBHashKey(attributeName = "PK")
        private val pk: String,
        @DynamoDBAttribute(attributeName = "openDate")
        @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter::class)
        private val openDate: LocalDateTime,
        @DynamoDBAttribute(attributeName = "deadlineDate")
        @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter::class)
        private val deadlineDate: LocalDateTime,
        @DynamoDBAttribute(attributeName = "limitedQuantityOf")
        private val limitedQuantityOf: Int,
        @DynamoDBAttribute(attributeName = "status")
        @DynamoDBTypeConverted(converter = DynamoDBStatusConverter::class)
        private val status: Status,
        @DynamoDBAttribute(attributeName = "createdAt")
        @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter::class)
        private var createdAt: LocalDateTime?,
        @DynamoDBAttribute(attributeName = "updatedAt")
        @DynamoDBTypeConverted(converter = DynamoDBLocalDateTimeConverter::class)
        private var updatedAt: LocalDateTime?
) {

    companion object {
        val PK_PREFIX = "subject#"
        val SK_INFO = "info"

        fun toPk(id: String?): String {
            return String.format("%s%s", PK_PREFIX, id)
        }
    }

    @DynamoDBRangeKey(attributeName = "SK")
    private val sk: String = SK_INFO

    constructor(subject: Subject): this(toPk(subject.getId()), subject.getOpenDate(), subject.getDeadlineDate(), subject.limitedQuantityOf, subject.getStatus(), null, null)

    fun toDomain(): Subject {
        return Subject(this.pk, this.limitedQuantityOf, this.openDate, this.deadlineDate, this.status)
    }

    fun setUpdatedAt(updatedAt: LocalDateTime?) {
        this.updatedAt = updatedAt
    }

    fun setCreatedAt(createdAt: LocalDateTime?) {
        this.createdAt = createdAt
    }

}