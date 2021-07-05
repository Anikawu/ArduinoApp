package com.example.arduinoapp.study1

fun main() {
    val chinese:Int =100
    val math:Int? =90  //?:表示math有可能是null
    var sum =0
    //計算總分1---------------------------
    if(math != null){
            sum = chinese + math
            print("總分: ${sum}")
    }
    //計算總分2---------------------------
    sum =chinese+(math?:0) //若math=null 計算時用 0 來表示
    print("總分: ${sum}")

    // 計算總分 3 ----------------
    try {
        sum = chinese + math!! // !! 表示 kotlin 不要管 null 的問題
        // 不管 math 如何我都要 +
        // 風險由工程師承擔
        // 通常會搭配 try-catch
        println("總分 ${sum}")
    } catch (e: Exception) {
        println("錯誤訊息: ${e}")
    }

}