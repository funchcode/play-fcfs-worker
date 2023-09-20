package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReservationChartTest {

    @Test
    @DisplayName("대상을 예약한다.")
    fun reserveSubject() {
        val subject = Subject()
        val clientId = ""
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = ReservationChart(subject)

        Assertions.assertTrue(chart.reserve(clientId, from, to))
    }

    @Test
    @DisplayName("이미 예약이 된 상태라면 이미 예약되어 있다는 예외가 발생한다.")
    fun reservedSubjectError() {
        val subject = Subject()
        val clientId = ""
        val from = LocalDateTime.now()
        val to = LocalDateTime.now()

        val chart = ReservationChart(subject)

        Assertions.assertThrows(AlreadyReservedException::class.java) {
            chart.reserve(clientId, from, to)
        }
    }

}