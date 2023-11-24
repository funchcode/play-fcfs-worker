package io.github.funch.fcfs.common

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import java.time.LocalDateTime


class DynamoDBLocalDateTimeConverter : DynamoDBTypeConverter<String?, LocalDateTime?> {

    override fun convert(date: LocalDateTime?): String? {
        return date?.toString()
    }

    override fun unconvert(value: String?): LocalDateTime? {
        return if (value == null) { null } else {LocalDateTime.parse(value)}
    }

}