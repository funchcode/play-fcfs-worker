package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

class DateTimeReservationChart(private val subject: Subject, private val reservationList: Set<Reservation> = HashSet()) : ReservationChart {

    private fun isReserved(from: LocalDateTime, to: LocalDateTime): Boolean {
//        reservationList.forEach { reservation ->
//        }
        return true
    }

    override fun available(from: LocalDateTime, to: LocalDateTime): Boolean {
        return !subject.closedDay(from, to) && !isReserved(from, to)
    }

    override fun reserve(clientId: String, from: LocalDateTime, to: LocalDateTime): Boolean {
        TODO("가능한 날짜인지 확인")

        TODO("이미 예약이 되어 있는지 확인")

        return false
    }

}