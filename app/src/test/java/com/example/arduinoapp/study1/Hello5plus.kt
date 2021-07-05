package com.example.arduinoapp.study1

/*
* 雞 + 兔 有 83 隻
* 雞的腳 + 兔的腳 有 240 隻
* 求 雞 與 兔 各有幾隻 ?
*
    83 * 2 = 166
    240 - 166 = 74
    74/(4-2) = 37 .. 兔子
    83 - 37  = 46 .. 雞
*/
fun printResult(amount: Int, feets: Int) {
    if (feets < amount * 2) {
        println("資料不正確")
        return // 不回傳任何值, 方法結束
    }
    if (feets > amount * 4) {
        println("資料不正確")
        return // 不回傳任何值, 方法結束
    }
    val rabbit = (feets - (amount * 2))/2
    val chichen = amount - rabbit

    println("雞: ${chichen} 兔子: ${rabbit}")
}

fun main() {
    printResult(83, 240)
    printResult(83, 200)
    printResult(83, 500)

}