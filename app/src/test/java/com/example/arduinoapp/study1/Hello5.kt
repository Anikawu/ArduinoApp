package com.example.arduinoapp.study1
/*
* 雞 + 兔 有 83 隻
* 雞的腳 + 兔的腳 有 240 隻
* 求 雞 與 兔 各有幾隻 ?
*
    83 * 2 = 166
    240 - 166 = 74
    74/(4-2) = 37 .. 兔子
    83 - 37  = 46 .. 雞
*/
fun main() {
    val amount = 83
    val feets = 240

    val rabbit = (feets - (amount * 2)) / 2
    val chichen = amount - rabbit

    println("雞: ${chichen} 兔子: ${rabbit}")


    var total = 83
    var foot = 240
    var i = 1
    for (i in i until total) {
        if (((i * 2) + ((total - i) * 4)) == foot) {
            println("雞: %d 隻,兔子: %d 隻".format(i, (total - i)))
        }
    }
}

