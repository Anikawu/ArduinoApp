package com.study.app_tickets_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity(), RecyclerViewAdapter.RowOnItemClickListener {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var userName: String
    lateinit var context: Context
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        context = this

        // 取得上一頁傳來的 userName 參數資料
        userName = intent.getStringExtra("userName").toString()
        title = "Hi " + userName + " 的雲端購票紀錄"
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                val orderList = mutableListOf<Order>()
                children.forEach {
                    if (it.key.toString() == "orders") {
                        tv_info.setText(it.child(userName).toString())
                        it.child(userName).children.forEach {
                            try {
                                val order = Order(
                                    userName,
                                    it.key.toString(),
                                    it.child("allTickets").value.toString().toInt(),
                                    it.child("roundTrip").value.toString().toInt(),
                                    it.child("oneWay").value.toString().toInt(),
                                    it.child("total").value.toString().toInt()
                                )
                                orderList.add(order)
                            } catch (e: Exception) {

                            }

                        }
                    }
                }
                // 更新 recycler view 的資訊
                recyclerViewAdapter.setOrders(orderList)
                // 通知 UI 變更
                recyclerViewAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        // init recycler view
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerViewAdapter = RecyclerViewAdapter(this@OrderListActivity)
            adapter = recyclerViewAdapter
            // 分隔線
            val divider = DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }

    }

    override fun onItemClickListener(order: Order) {
        val key = order.key
        val alert = AlertDialog.Builder(context)
        alert.setTitle("退票")
        alert.setMessage("$key 是否要退票 ?")
        alert.setPositiveButton("是") { dialog, which ->
            //刪除訂單紀錄
            myRef.child("orders/" + userName + "/" + key).removeValue()
            //票數加回
            //order.alltickets
            val returnTickets = TicketsStock.totalAmount + order.allTickets
            myRef.child("totalAmount").setValue(returnTickets)
        }
        alert.setNegativeButton("否", null)
        alert.show()
        //Toast.makeText(context, order.toString(), Toast.LENGTH_SHORT).show()
    }

}