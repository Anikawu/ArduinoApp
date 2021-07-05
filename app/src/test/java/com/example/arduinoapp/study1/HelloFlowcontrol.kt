package com.example.arduinoapp.study1
import kotlin.random.Random as r

fun main() {
    // if
    val score = r.nextInt(101) // 0~100
    val pass = if(score >= 60) "Pass" else "Fail"
    println("$score $pass")

    // 100~90 "A", 98~80 "B", 79~70 "C", 69~60 "D", 59~0 "E"
    // when (類似 java, c/c++ 的 switch-case)
    when(score) {
        in 90..100 -> println("A")
        in 80..89 -> println("B")
        in 70..79 -> println("C")
        in 60..69 -> println("D")
        else -> println("E")
    }

    val level = when(score) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "E"
    }
    println(level)

    val level2 = when(score/10) {
        10, 9 -> "A"
        8 -> "B"
        7 -> "C"
        6 -> "D"
        else -> "E"
    }
    println(level2)

    //for
    for(i in 1..10){
        print("$i")
    }
    println()
    for(i in 1..10 step 2){
        print("$i")
    }
    println()
    println()
    for(i in 1 until 10){ //不含10
        print("$i")
    }
    println()
}