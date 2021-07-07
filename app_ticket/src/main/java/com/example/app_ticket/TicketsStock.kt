package com.example.app_ticket


class TicketsStock {
    companion object {
        var totalAmount = 30
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}