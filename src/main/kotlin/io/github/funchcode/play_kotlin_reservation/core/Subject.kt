package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDate
import java.time.LocalDateTime

class Subject {

    val id: String = ""
    val closedDays: List<LocalDate>? = null

    fun closedDay(from: LocalDate, to: LocalDate): Boolean {
        return false
    }

}