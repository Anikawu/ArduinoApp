package com.example.arduinoapp.study1

fun main() {
    //數組陣列
    val num1 = arrayOf(1,5,"7",3.0)  //隱式陣列
    var num2 = arrayOf<Int>(1,5,7,3) //顯示陣列

    //for-loop 印出(走訪)每一個元素
    for(i in num1.indices){ //num1.indices=0..num1.size-1  長度-1
        println(num1[i])

    }
    //contentToString 直接印出陣列內容 (Array.toString(xx))
    println(num1.contentToString())
    println(num2.contentToString())
    val num3 = Array(5,{i -> i*2}) //i從0開始
    println(num3.contentToString())
    val num4 = Array(5,{i -> i*1}) //i從0開始
    println(num4.contentToString())
    //使用 lambda 語法印出(走訪)每一個元素
    num4.forEach { n->println("n =${n}")}
    num4.forEach { println(it) }

}