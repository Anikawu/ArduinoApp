package com.example.arduinoapp.study1
import kotlin.random.Random as r
// 動動腦
// 有一的1~10的亂數 ans , 給使用者來猜, 猜對 Bingo, 有五次機會
// 加入請猜小/大一點的提示
fun main() {

    val ans = r.nextInt(1, 11)
    val max = 5
    for (i in 1..max) {
        print("第 $i/$max 次: 請猜一個數字:")
        val guess = readLine()!!.toInt()
        if(guess == ans) {
            println("Bingo")
            break
        } else if(guess > ans) {
            println("請猜小一點")
        } else {
            println("請猜大一點")
        }
    }
}