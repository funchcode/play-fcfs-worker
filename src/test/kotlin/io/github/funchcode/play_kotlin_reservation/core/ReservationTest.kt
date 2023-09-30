package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ReservationTest {

    @Test
    @DisplayName("예약된 날짜인지 확인한다.")
    fun isReservedDate() {
        val today = LocalDateTime.now()
        val tommorrw = LocalDateTime.now().plusDays(1L)
        val reservation = Reservation("sub-001", "client-001", today, tommorrw)

        Assertions.assertTrue(reservation.isReservedDate(today, today))
    }

}