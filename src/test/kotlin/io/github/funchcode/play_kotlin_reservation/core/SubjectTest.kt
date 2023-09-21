package io.github.funchcode.play_kotlin_reservation.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SubjectTest {

    @Test
    @DisplayName("전달한 날이 휴관일인지 확인")
    fun closedDay() {
        val subject = Subject()
        val from = LocalDate.now()
        val to = LocalDate.now()

        Assertions.assertTrue(subject.closedDay(from, to))
    }

}