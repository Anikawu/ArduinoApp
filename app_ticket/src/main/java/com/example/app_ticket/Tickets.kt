package com.example.app_ticket


import kotlin.math.round

class Tickets(var allTickets: Int, var roundTrip: Int) {
    val price: Int = 1000
    val discountRate: Double = 0.9
    var oneWay: Int = 0
    init {
        oneWay = allTickets - roundTrip * 2
    }
    fun total(): Int {
        // 單程票價1000元，來回票為2000元再打九折
        val total = oneWay * price + (roundTrip * (price * 2)) * discountRate
        return round(total).toInt()
    }
}