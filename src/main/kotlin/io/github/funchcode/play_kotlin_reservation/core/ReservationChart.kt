package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

sealed interface ReservationChart {

    fun available(from: LocalDateTime, to: LocalDateTime): Boolean

    fun reserve(clientId: String, from: LocalDateTime, to: LocalDateTime): Boolean

}