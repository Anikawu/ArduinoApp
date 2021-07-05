package com.example.arduinoapp.study1

fun main() {
    val set = setOf<Int>(1,3,5,7,1)
    val list = listOf<Int>(1,3,5,7,1)
    val map = mapOf<Int,String>(1 to "One",2 to "Two",3 to "Three")

    println(set)
    println(list)
    println(map)
    println("---------------")
    //set.iterator()
    var iter = set.iterator()
    println(iter.next())
    println(iter.next())
    set.forEach{ println(it)}
    println("list----------------")
    //list 取得指定元素
    println(list[0])
    list.forEach{ println(it)}
    println("map----------------")
    // map  取得指定元素
    println(map.get(2))
    println(map[2])
    map.forEach{println(it)}
}