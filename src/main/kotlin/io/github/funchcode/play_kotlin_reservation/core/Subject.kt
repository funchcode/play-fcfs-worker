package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

interface Subject {

    fun getId(): String
    fun closedDay(from: LocalDateTime, to: LocalDateTime): Boolean

}