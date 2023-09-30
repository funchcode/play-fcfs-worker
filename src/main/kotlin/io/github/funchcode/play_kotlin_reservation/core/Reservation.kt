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
        if (to.isAfter(this.from) && to.isBefore(this.to)) {
            return true
        }
        if (from.isAfter(this.from) && from.isBefore(this.to)) {
            return true
        }
        return false
    }

}