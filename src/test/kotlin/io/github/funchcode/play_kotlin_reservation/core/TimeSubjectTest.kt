package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class TimeSubjectTest {

    @Test
    @DisplayName("전달한 날이 휴일인지 확인")
    fun closedDay() {
        val closedDays = HashSet<LocalDate>()
        closedDays.add(LocalDate.now())
        val timeSubject = TimeSubject("sbj-t-01", closedDays)
        val from = LocalDateTime.now()
        val to = LocalDateTime.now().plusSeconds(1L)

        Assertions.assertTrue(timeSubject.closedDay(from, to))
    }

    @Test
    @DisplayName("휴일을 확인할 날짜 정보를 잘못 전달하는 경우 예외 발생")
    fun closedDayLocalDateError() {
        val timeSubject = TimeSubject("sbj-t-01", null)
        val from = LocalDateTime.now().plusDays(1L)
        val to = LocalDateTime.now()

        Assertions.assertThrows(ReservationTimeException::class.java) {
            timeSubject.closedDay(from, to)
        }
    }

}