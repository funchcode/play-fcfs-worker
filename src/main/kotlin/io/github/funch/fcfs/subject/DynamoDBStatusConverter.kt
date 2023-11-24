package io.github.funch.fcfs.subject

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter


class DynamoDBStatusConverter : DynamoDBTypeConverter<String?, Status?> {

    override fun convert(status: Status?): String? {
        return status?.name
    }

    override fun unconvert(value: String?): Status? {
        return if (value == null) { null } else { Status.valueOf(value) }
    }

}