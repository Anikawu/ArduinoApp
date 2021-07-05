package com.example.arduinoapp.study1

//抽象類別
//紅茶 10,奶茶 25

abstract class Tea(val name:String,val price:Int){
    fun printTeaInfo(){
        println("$name $$price")
    }
    abstract fun printIngredientsOfDrink() //飲料成分
}

class RedTea(name: String, price:Int): Tea(name,price){
    override fun printIngredientsOfDrink(){
        println("紅茶 + 水")
    }
}

class MilkTea(name: String, price:Int): Tea(name,price){
    override fun printIngredientsOfDrink(){
        println("茶 + 牛奶 + 水")
    }
}

fun main() {
    val redTea = RedTea("錫蘭紅茶", 10)
    val milkTea = MilkTea("厚奶茶", 25)
    val milkGeenTea = MilkTea("奶綠茶", 30)
    val tea = listOf<Tea>(redTea, milkTea, milkGeenTea)
    tea.forEach { it.printIngredientsOfDrink() }
    tea.forEach { it.printTeaInfo() }
    tea.forEach { println(it.name) }
    tea.forEach { println(it.price) }
    //回家作業
    var sum =0
    tea.forEach{ sum+=it.price}
    var avg = sum /tea.size
    println(avg)
    println("---------寫法2------------")
    /*
   * [Person(name=John, age=18), Person(name=Mary, age=19), Person(name=Helen, age=20)]
   * mapToInt 轉換為 Int 元素
   * [18, 19, 20]
   * */
    var avg2 = tea.stream().mapToInt{ it.price }.average().asDouble
    println(avg2)
    println("----------------------------")
    val stat = tea.stream().mapToInt { it.price }.summaryStatistics()
    println("統計資訊：$stat")
    println("平均：${stat.average}")

}