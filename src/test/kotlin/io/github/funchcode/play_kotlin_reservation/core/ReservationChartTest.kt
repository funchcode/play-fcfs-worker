package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReservationChartTest {

    @Test
    @DisplayName("예약하는 날이 휴일인지 확인한다.")
    fun availableReservation() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = ReservationChart(timeSubject)

        Assertions.assertTrue(chart.available(from, to))
    }

    @Test
    @DisplayName("이미 예약이 되어 있는지 확인한다.")
    fun checkAlreadyReserved() {
        val reserved = Reservation("random-001", "client-001", LocalDateTime.of(2023, 5, 5, 11, 11, 11), LocalDateTime.of(2023, 5, 5, 11, 31, 11))
        val timeSubject = TimeSubject("sbj-t-01", null)
        val from = LocalDateTime.of(2023, 5, 5, 10, 11, 11)
        val to = LocalDateTime.of(2023, 5, 5, 11, 15, 11)

        val chart = ReservationChart(timeSubject, hashSetOf(reserved))

        Assertions.assertFalse(chart.available(from, to))
    }

    @Test
    @DisplayName("대상을 예약한다.")
    fun reserveSubject() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val clientId = ""
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = ReservationChart(timeSubject)

        Assertions.assertNotNull(chart.reserve(clientId, from, to))
    }

}