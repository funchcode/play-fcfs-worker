package io.github.funch.fcfs.subject

interface SubjectRepository {

    fun findById(subjectId: String): Subject

}