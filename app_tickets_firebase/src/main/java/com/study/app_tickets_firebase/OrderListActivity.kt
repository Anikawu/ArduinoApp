package com.study.app_tickets_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        // 取得上一頁傳來的 userName 參數資料
        userName = intent.getStringExtra("userName").toString()
        title ="Hi "+ userName + " 的雲端購票"
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    if(it.key.toString() == "orders") {
                        tv_info.setText(it.child(userName).toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}