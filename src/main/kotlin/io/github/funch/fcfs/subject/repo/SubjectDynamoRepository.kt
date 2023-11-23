package io.github.funch.fcfs.subject.repo

import io.github.funch.fcfs.subject.Subject
import io.github.funch.fcfs.subject.SubjectRepository

class SubjectDynamoRepository : SubjectRepository {

    override fun findById(subjectId: String): Subject {
        TODO("Not yet implemented")
    }

}