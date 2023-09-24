package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDate
import java.time.LocalDateTime

class Subject(private val id: String, private val closedDays: Set<LocalDate>?) {

    fun closedDay(from: LocalDate, to: LocalDate): Boolean {
        if (from.isAfter(to)) {
            throw ReservationTimeException()
        }
        closedDays?.let {
            val fromDayIsClosedDay = it.contains(from)
            if (fromDayIsClosedDay) {
                return true
            }
            val toDayIsClosedDay = it.contains(to)
            if (toDayIsClosedDay) {
                return true
            }

        }
        return false
    }

}