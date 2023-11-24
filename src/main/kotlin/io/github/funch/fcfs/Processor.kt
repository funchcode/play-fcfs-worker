package io.github.funch.fcfs

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.google.gson.Gson
import io.github.funch.fcfs.common.Configs
import io.github.funch.fcfs.mq.TicketMessage
import io.github.funch.fcfs.subject.SubjectRepository
import io.github.funch.fcfs.subject.repo.SubjectDynamoRepository
import io.github.funch.fcfs.ticket.TicketRepository
import io.github.funch.fcfs.ticket.Ticketer
import io.github.funch.fcfs.ticket.repo.TicketDynamoRepository
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.model.Message

class Processor {

    private val dynamoDBClient = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(Configs.getAwsCredentialAccessKey(), Configs.getAwsCredentialSecretKey())))
            .withRegion(Region.AP_NORTHEAST_2.id())
            .build()
    private val dynamoDBMapper = DynamoDBMapper(dynamoDBClient)
    private val subjectRepository: SubjectRepository = SubjectDynamoRepository(dynamoDBMapper)
    private val ticketRepository: TicketRepository = TicketDynamoRepository(dynamoDBMapper, subjectRepository)

    fun processMessage(message: Message) {
        val ticketMessage = Gson().fromJson(message.body(), TicketMessage::class.java)
        Ticketer(subjectRepository, ticketRepository).ticketing(ticketMessage.subjectId, ticketMessage.clientId)
    }

}