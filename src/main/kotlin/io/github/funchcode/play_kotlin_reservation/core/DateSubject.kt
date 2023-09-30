package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDate
import java.time.LocalDateTime

class DateSubject(private val id: String, private val closedDays: Set<LocalDate>?) : Subject {

    override fun closedDay(from: LocalDateTime, to: LocalDateTime): Boolean {
        if (from.isAfter(to)) {
            throw ReservationTimeException()
        }

        val fromDate = from.toLocalDate()
        val toDate = to.toLocalDate()

        closedDays?.let {
            val fromDayIsClosedDay = it.contains(fromDate)
            if (fromDayIsClosedDay) {
                return true
            }
            val toDayIsClosedDay = it.contains(toDate)
            if (toDayIsClosedDay) {
                return true
            }
        }

        return false
    }
}