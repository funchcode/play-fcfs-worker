package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

class TimeReservationChart(subject: Subject): ReservationChart {

    override fun available(from: LocalDateTime, to: LocalDateTime): Boolean {
        return false
    }

    override fun reserve(clientId: String, from: LocalDateTime, to: LocalDateTime): Boolean {
        return false
    }

}