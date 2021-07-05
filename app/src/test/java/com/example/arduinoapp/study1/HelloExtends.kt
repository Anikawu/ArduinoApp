package com.example.arduinoapp.study1

// 抽象類別
abstract class Human(val sex: String) {
    fun printMySex() {
        println("My sex is $sex")
    }
    abstract fun eat()
}

class Man(sex: String): Human(sex) {
    override fun eat() {
        println("$sex 大口吃飯")
    }
}

class Woman(sex: String) : Human(sex) {
    override fun eat() {
        println("$sex 小口吃飯")
    }
}

data class Student(val name: String, val score: Int, val human: Human)

fun main() {
    val m = Man("男人")
    val f = Woman("女人")
    val s1 = Student("John", 100, m)
    println(s1)
    println(s1.name)
    println(s1.score)
    s1.human.eat()
    println(s1.human.sex)
}

//fun main() {
//    val m = Man("男人")
//    val f = Woman("女人")
//    val list = listOf<Human>(m, m, m, f, f, m)
//    list.forEach { it.eat() }
//    m.printMySex()
//    f.printMySex()
//}