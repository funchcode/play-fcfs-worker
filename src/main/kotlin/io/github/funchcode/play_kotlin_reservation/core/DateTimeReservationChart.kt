package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

class DateTimeReservationChart(subject: Subject) : ReservationChart {

    override fun available(from: LocalDateTime, to: LocalDateTime): Boolean {
        TODO("")
        return true
    }

    override fun reserve(clientId: String, from: LocalDateTime, to: LocalDateTime): Boolean {
        TODO("가능한 날짜인지 확인")

        TODO("이미 예약이 되어 있는지 확인")

        return false
    }

}