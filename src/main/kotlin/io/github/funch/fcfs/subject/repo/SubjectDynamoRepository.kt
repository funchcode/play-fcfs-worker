package io.github.funch.fcfs.subject.repo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import io.github.funch.fcfs.subject.Subject
import io.github.funch.fcfs.subject.SubjectRepository
import java.util.*

class SubjectDynamoRepository(private val dynamoDBMapper: DynamoDBMapper) : SubjectRepository {

    override fun findById(subjectId: String): Optional<Subject> {
        return findByPk(SubjectDao.toPk(subjectId))
    }

    private fun findByPk(pk: String): Optional<Subject> {
        val subjectDao = dynamoDBMapper.load(SubjectDao::class.java, pk, SubjectDao.SK_INFO) ?: return Optional.empty()
        return Optional.ofNullable(subjectDao.toDomain())
    }

}