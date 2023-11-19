package io.github.funch.fcfs.ticket

import io.github.funch.fcfs.subject.Subject

data class Ticket (
        val subject: Subject,
        val clientId: String,
) {

    fun checkIssueable() {
        subject.checkIssueable()
    }

}