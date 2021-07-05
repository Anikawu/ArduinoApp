package com.example.arduinoapp.study1

import kotlin.random.Random

fun main() {
    //利用lambda 進行四星彩電腦選號
    //四星彩=四個數字 每一個數字是0-9 且可以重錄
    //例如 1332
    //Random.nextInt(10) 表示亂數產生0-9
    val stars = Array(4){ _ -> Random.nextInt(10)}
    println(stars.contentToString())
    println(stars[0])
    println(stars.get(0))
    //改變元素內容
    stars.set(0,10) //將維度0的內容 改成10
    println(stars.contentToString())
}