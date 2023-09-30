package io.github.funchcode.play_kotlin_reservation.core

import java.time.LocalDateTime

class Reservation(subjectId: String, clientId: String, val from: LocalDateTime, val to: LocalDateTime) {

    fun isReservedDate(from: LocalDateTime, to: LocalDateTime): Boolean {
        if (this.from.isEqual(from) || this.to.isEqual(from)) {
            return true
        }
        if (this.from.isEqual(to) || this.to.isEqual(to)) {
            return true
        }
        if (this.from.isAfter(to) && this.to.isBefore(to)) {
            return true
        }
        if (this.from.isAfter(from) && this.from.isBefore(from)) {
            return true
        }
        return false
    }

}