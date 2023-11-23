package io.github.funch.fcfs.subject

import java.time.LocalDateTime

class Subject {
    private val id: String
    val limitedQuantityOf: Int
    private val openDate: LocalDateTime
    private val deadlineDate: LocalDateTime
    private val status: Status

    constructor(id: String, limitedQuantityOf: Int, openDate: LocalDateTime, deadlineDate: LocalDateTime, status: Status) {
        if (limitedQuantityOf <= 0) {
            //0이하의 수량을 설정할 수 없습니다.
            throw TicketIssueException()
        }
        if (openDate.isAfter(deadlineDate)) {
            // "오픈 날짜는 마감 날짜 이후로 설정할 수 없습니다."
            throw TicketIssueException()
        }
        if (deadlineDate.isBefore(LocalDateTime.now())) {
            //마감 날짜는 과거로 설정할 수 없습니다.
            throw TicketIssueException()
        }
        this.id = id
        this.limitedQuantityOf = limitedQuantityOf
        this.openDate = openDate
        this.deadlineDate = deadlineDate
        this.status = status
    }

    /**
     * 발급 가능한 기간인지 확인
     */
    private fun issueablePeriod(): Boolean {
        val today = LocalDateTime.now()
        return openDate.isBefore(today) && deadlineDate.isAfter(today);
    }

    /**
     * 발급 가능한 Subject 상태인지 확인
     */
    private fun issueableStatus(): Boolean {
        return Status.ONGOING.equals(status)
    }

    fun checkIssueable() {
        if (!issueableStatus()) {
            // "발급할 수 없는 상태입니다."
            throw TicketIssueException()
        }
        if (!issueablePeriod()) {
            // "발급할 수 없는 기간입니다."
            throw TicketIssueException()
        }
    }

}