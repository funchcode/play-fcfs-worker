package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDate
import java.time.LocalDateTime

class TimeSubject(private val id: String, private val closedDays: Set<LocalDate>?) : Subject {

    override fun closedDay(from: LocalDateTime, to: LocalDateTime): Boolean {
        if (from.isAfter(to)) {
            throw ReservationTimeException()
        }

        closedDays?.let {
            val fromDayIsClosedDay = it.contains(from.toLocalDate())
            if (fromDayIsClosedDay) {
                return true
            }
            val toDayIsClosedDay = it.contains(to.toLocalDate())
            if (toDayIsClosedDay) {
                return true
            }
        }

        return false
    }

}