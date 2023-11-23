package io.github.funch.fcfs.ticket

import io.github.funch.fcfs.subject.Subject

data class Ticket (
        val subject: Subject,
        val clientId: String,
) {

    companion object {
        fun newInstance(subject: Subject, clientId: String): Ticket {
            return Ticket(subject, clientId)
        }
    }

    fun checkIssueable() {
        subject.checkIssueable()
    }

}