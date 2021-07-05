package com.example.arduinoapp.study1

fun main() {
    var r = 10.5
    //園面積
    var area = r * r * Math.PI
    print(area)
    area =Math.pow(r,2.0) * Math.PI
    println(area)
    println("%f".format(area)) // %f:放一個浮點數
    println("圓面積 = %.2f".format(area))
    println("半徑 = %.1f,圓面積=%.2f".format(r,area))
    println("圓面積 = $area")
    println("半徑= $r,圓面積 = $area")
    //球體積
    var A =(4/3.0)* (Math.PI * Math.pow(r,3.0))
    println("半徑=$r, 球體積=$A")
    println("半徑= %.1f ,球體積=%.2f".format(r,A))



}