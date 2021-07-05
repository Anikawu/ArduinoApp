package com.example.arduinoapp.study1

// BMI 計算
// 身高 = 170(cm), 體重 = 60(kg)
// 求 BMI = ?

fun getBMI(h: Double, w: Double): Double {
    val bmi = w / Math.pow(h/100.0, 2.0)
    return bmi
}

fun main() {
    val h = 170 // Int
    val w = 60  // Int
    val bmi = w / Math.pow(h/100.0, 2.0)
    println("%.2f".format(bmi))
    val bmi2 = getBMI(h.toDouble(), w.toDouble())
    println("%.2f".format(bmi2))
}