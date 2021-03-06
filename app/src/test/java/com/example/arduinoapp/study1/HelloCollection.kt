package com.example.arduinoapp.study1

fun main() {
    //集合(高級陣列):set,list,map
    //set: 元素不會重複 ex: [1,3,5,7] or [甲 乙 丙]
    //list :元素可重複  ex:[1,3,5,7] or [100,90,90]
    //map: 每個元素都是 key/value 的組合 ex:[甲:100,乙:90,丙:90]
    //               key:[甲 乙 丙], value:[100,90,90]
    val set = setOf<Int>(1,5,2,7,6,3,1)
    //set.add(9) //不可增加元素
    println(set)  //[1,5,2,7,3,6,3]
    val set2 = mutableSetOf<Int>(1,5,2,7,6,3,1)
    //set2.add(9)//可增加元素(mutable 表示該集合元素可以新增修改刪除)
    println(set2)
    //最大值
    println(set2.maxOrNull())
    //有條件的最大值
    //除以3餘數的最大值
    //[10,42,5,4] %3 [1,0,2,1]
    val nums = setOf<Int>(10,42,5,4)
    println(nums.maxByOrNull { n -> n %3 })
    println(nums.maxByOrNull { it % 3 })
    //過濾資料
    val score = setOf<Int>(80,40,50,90)
    println(score.filter { it >=60 })
    println(score.filter { it >=60 }.maxOrNull())

    //字串長度
    val name ="John"
    val len =name.length
    println("$name 的長度 $len")
    println("$name 的長度 ${name.length}")
    //印出名字最長的
    val names = setOf<String>("Helen","John","Jackson","Anita")

    println(names.maxByOrNull { it.length })



}