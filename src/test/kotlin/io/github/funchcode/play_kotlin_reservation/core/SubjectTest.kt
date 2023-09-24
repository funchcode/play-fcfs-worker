package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SubjectTest {

    @Test
    @DisplayName("전달한 날이 휴관일인지 확인")
    fun closedDay() {
        val closedDays = HashSet<LocalDate>()
        closedDays.add(LocalDate.now())
        val subject = Subject("sbj-t-01", closedDays)
        val from = LocalDate.now()
        val to = LocalDate.now()

        Assertions.assertTrue(subject.closedDay(from, to))
    }

    @Test
    @DisplayName("휴관일을 확인할 날짜 정보를 잘못 전달하는 경우 예외 발생")
    fun closedDayLocalDateError() {
        val subject = Subject("sbj-t-01", null)
        val from = LocalDate.now().plusDays(1L)
        val to = LocalDate.now()

        Assertions.assertThrows(ReservationTimeException::class.java) {
            subject.closedDay(from, to)
        }
    }

}