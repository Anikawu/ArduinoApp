package com.example.app_ticket

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    // App 進入點
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        initUI()
    }

    // 初始欄位資料
    fun initUI() {
        // 配置剩餘張數
        //val tv_total_amount = findViewById<TextView>(R.id.tv_total_amount)
        tv_total_amount.setText(TicketsStock.totalAmount.toString())
        // 清除結帳資訊
        var result = resources.getString(R.string.ticket_result)
        result = String.format(result, 0, 0, 0, 0)
        tv_result.setText(result)
        // 清除警告訊息
        tv_warning.setText("None...")
    }

    // 清除欄位資料(按下清除鈕)
    fun reset(view: View) {
        initUI()
        et_all_tickets.setText("0")
        et_round_trip.setText("0")
    }

    // 驗證購票欄位
    fun confirmTickets(): Boolean {
        // 1. 檢查各欄位的資料是否有值, 若沒有值則自動補 0
        if(et_all_tickets.text == null || et_all_tickets.text.toString().equals("")) {
            et_all_tickets.setText("0")
        }
        if(et_round_trip.text == null || et_round_trip.text.toString().equals("")) {
            et_round_trip.setText("0")
        }

        val allTickets = et_all_tickets.text.toString().toInt()
        val roundTrip  = et_round_trip.text.toString().toInt()

        // 2. 購買票數 > 0
        if(allTickets <= 0) {
            tv_warning.setText("購買票數必須 > 0")
            Toast.makeText(context, "購買票數必須 > 0", Toast.LENGTH_SHORT).show()
            return false
        }

        // 3. 檢查剩餘票數是否足夠 ?
        val totalAmount = tv_total_amount.text.toString().toInt()
        if (allTickets > totalAmount) {
            tv_warning.setText("剩餘票數不足 !")
            Toast.makeText(context, "剩餘票數不足 !", Toast.LENGTH_SHORT).show()
            return false
        }
        // 4. 來回票組數是否設定正確 ?
        if(allTickets < roundTrip * 2) {
            tv_warning.setText("來回票組數過多 !")
            Toast.makeText(context, "剩餘票數不足 !", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // 購票流程(按下購買結帳鈕)
    fun buyTicket(view: View) {
        // 檢驗票數資訊
        if(!confirmTickets()) {
            return
        }

        val allTickets = et_all_tickets.text.toString().toInt()
        val roundTrip  = et_round_trip.text.toString().toInt()

        // 進行票務處理
        val tickets = Tickets(allTickets, roundTrip)
        //  result = 總票數：%d\n來回票：%d\n單程票：%d\n總金額：$%,d
        var result = resources.getString(R.string.ticket_result)
        result = String.format(result,
            tickets.allTickets,
            tickets.roundTrip,
            tickets.oneWay,
            tickets.total())
        tv_result.setText(result)
        // update 剩餘張數
        TicketsStock.subAmount(tickets.allTickets)
        tv_total_amount.setText(TicketsStock.totalAmount.toString())
        // 購買成功訊息
        tv_warning.setText("購買成功 !")
        Toast.makeText(context, "購買成功 !", Toast.LENGTH_SHORT).show()
    }
}