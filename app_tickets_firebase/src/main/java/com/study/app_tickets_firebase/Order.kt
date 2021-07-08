package com.study.app_tickets_firebase


data class Order(
    val userName: String,
    val key: String,
    val allTickets: Int,
    val roundTrip: Int,
    val oneWay: Int,
    val total: Int,)
