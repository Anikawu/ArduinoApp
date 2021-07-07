package com.study.app_tickets_firebase

import kotlin.math.round

class Tickets(var allTickets: Int, var roundTrip: Int) {

    var oneWay: Int = 0
    init {
        oneWay = allTickets - roundTrip * 2
    }
    fun total(): Int {
        val price = TicketsStock.price
        val discount = TicketsStock.discount
        // 單程票價1000元，來回票為2000元再打九折
        val total = oneWay * price + (roundTrip * (price * 2)) * discount
        return round(total).toInt()
    }
}