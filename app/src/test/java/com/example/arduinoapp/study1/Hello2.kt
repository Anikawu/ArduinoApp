package com.example.arduinoapp.study1

fun main() {
    val s ="100"
    val i =100
    //print(s == i) 不同型別不可以直接比較
    print(s==i.toString()) //把i轉成字串
    //"100"==100 經過tostring() 變成"100"="100"
    print(s.equals(i.toString()))
    //將字串的"100"變成 數字的100
    print(s.toInt()==i)
}