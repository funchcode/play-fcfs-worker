package io.github.funch.fcfs.subject

import java.util.*

interface SubjectRepository {

    fun findById(subjectId: String): Optional<Subject>

}