package com.example.arduinoapp.study1

data class Person(val name: String, val age:Int )

fun main() {
    val p1 = Person("John",18)
    val p2 = Person("Mary",19)
    val p3 = Person("Helen",20)
    println(p1);println(p2);println(p3)
    println("--------------------------------")
    //印出年齡最大的人
    val people = listOf<Person>(p1,p2,p3)
    val person = people.maxByOrNull { it.age }
    if(person != null){

        println(person.name)
    }
    println(person?.name) //若person = null ,則印出null
    println(person!!.name) //忽略null值得警告 最好配合try-catch
    println("-----------自己寫法-------------")
    //請問平均年齡
    val sum= people.sumOf { it.age }
    val number =people.count()
    val avg = sum/number
    println(avg)
    println("---------老師寫法1------------")
    /**
     * **/
    var sum2 =0
    people.forEach{ sum2+=it.age}
    var avg2 = sum /people.size
    println(avg2)
    println("---------老師寫法2------------")
    /*
   * [Person(name=John, age=18), Person(name=Mary, age=19), Person(name=Helen, age=20)]
   * mapToInt 轉換為 Int 元素
   * [18, 19, 20]
   * */
    var avg3 = people.stream().mapToInt{ it.age }.average().asDouble
    println(avg3)
    println("----------------------------")
    val stat = people.stream().mapToInt { it.age }.summaryStatistics()
    println("統計資訊：$stat")
    println("平均：${stat.average}")

}