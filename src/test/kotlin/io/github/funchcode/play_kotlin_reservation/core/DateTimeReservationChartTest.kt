package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DateTimeReservationChartTest {

    @Test
    @DisplayName("예약하는 날이 휴일인지 확인한다.")
    fun availableReservation() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = DateTimeReservationChart(timeSubject)

        Assertions.assertTrue(chart.available(from, to))
    }

    @Test
    @DisplayName("이미 예약이 되어 있는지 확인한다.")
    fun checkAlreadyReserved() {
        val reserved = Reservation("random-001", "client-001", LocalDateTime.now(), LocalDateTime.now())
        val timeSubject = TimeSubject("sbj-t-01", null)
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = DateTimeReservationChart(timeSubject, hashSetOf(reserved))

        Assertions.assertFalse(chart.available(from, to))
    }

    @Test
    @DisplayName("대상을 예약한다.")
    fun reserveSubject() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val clientId = ""
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = DateTimeReservationChart(timeSubject)

        Assertions.assertTrue(chart.reserve(clientId, from, to))
    }

    @Test
    @DisplayName("이미 예약이 된 상태라면 이미 예약되어 있다는 예외가 발생한다.")
    fun reservedSubjectError() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val clientId = ""
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = DateTimeReservationChart(timeSubject)

        Assertions.assertThrows(AlreadyReservedException::class.java) {
            chart.reserve(clientId, from, to)
        }
    }

}