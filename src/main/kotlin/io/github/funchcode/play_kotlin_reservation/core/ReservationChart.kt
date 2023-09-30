package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

class ReservationChart(private val subject: Subject, private val reservationList: Set<Reservation> = HashSet()) {

    private fun isReserved(from: LocalDateTime, to: LocalDateTime): Boolean {
        reservationList.forEach { reservation ->
            if (reservation.isReservedDate(from, to)) {
                return true
            }
        }
        return false
    }

    fun available(from: LocalDateTime, to: LocalDateTime): Boolean {
        return !subject.closedDay(from, to) && !isReserved(from, to)
    }

    fun reserve(clientId: String, from: LocalDateTime, to: LocalDateTime): Reservation? {
        if (!available(from, to)) {
            return null
        }
        return Reservation(subject.getId(), clientId, from, to)
    }

}