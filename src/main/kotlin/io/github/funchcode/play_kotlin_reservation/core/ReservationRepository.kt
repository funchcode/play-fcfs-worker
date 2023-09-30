package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDate
import java.time.LocalDateTime

interface ReservationRepository {

    fun Save(subjectId: String, clientId: String, from: LocalDateTime, to: LocalDateTime): Reservation

}