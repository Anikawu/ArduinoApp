package com.example.arduinoapp.study2
/*
假設只有台北到高雄的票，單程票價1000元，來回票為2000元再打九折
請設計Kotlin讓它執行後完成以下規範 :
1. 詢問使用者要購買張數，如下:
Please enter number of tickets:
2. 取得使用者輸入的張數後，再詢問幾張來回票，如下:
How many round-trip tickets:
3. 最後印出本次的張數、來回票數與總金額，如下:
Total tickets: 5 (5-2*2 = 1, 1 * 1000 = 2000)
Round-trip: 2 (2000*2*0.9 = 3600)
Total: 5600
*/
import kotlin.math.round


class TicketsStock {
    companion object {
        var totalAmount = 20
        fun subAmount(amount: Int) {
            totalAmount = totalAmount - amount
        }
    }
}

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

fun buyTickets() {
    println("剩餘張數: ${ TicketsStock.totalAmount }")
    print("請輸入購買張數:")
    val allTickets = readLine()!!.toInt()
    print("請輸入來回票組數:")
    val roundTrip = readLine()!!.toInt()
    //-----------------------------------------------------------------------------
    // 購買張數不可大於剩餘張數
    if(allTickets > TicketsStock.totalAmount) {
        println("購買張數: ${allTickets} 不可大於剩餘張數: ${TicketsStock.totalAmount}")
        return
    }
    // 來回組數的票數不可以大於總購買張數
    if(roundTrip * 2 > allTickets) {
        println("來回組數的票數: ${roundTrip * 2} 不可以大於總購買張數: ${allTickets}")
        return
    }
    //-----------------------------------------------------------------------------
    val tickets = Tickets(allTickets, roundTrip)
    println("總票數：${tickets.allTickets} " +
            "來回票: ${tickets.roundTrip} " +
            "單程票: ${tickets.oneWay}" +
            "總金額: ${tickets.total()}")
    //------------------------------------------
    TicketsStock.subAmount(tickets.allTickets)
    println("剩餘張數: ${ TicketsStock.totalAmount }")
}

fun main() {
    while (true) {
        buyTickets()
        print("是否要繼續購買(1:繼續, 0:離開)？")
        val exit = readLine()!!.toInt()
        if(exit == 0) {
            break
        }
    }
    println("謝謝惠顧，再會 !")
}
