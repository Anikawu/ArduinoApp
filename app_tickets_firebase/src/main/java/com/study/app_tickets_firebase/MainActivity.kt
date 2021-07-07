package com.study.app_tickets_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    Log.d("MainActivity",
                        it.key.toString() + ":" + it.value.toString())
                    when(it.key.toString()) {
                        "discount" -> TicketsStock.discount = it.value.toString().toDouble()
                        "price" -> TicketsStock.price = it.value.toString().toInt()
                        "totalAmount" -> TicketsStock.totalAmount = it.value.toString().toInt()
                    }
                }
                refreshUI()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun refreshUI() {
        tv_ticket_price.setText(TicketsStock.price.toString())
        tv_ticket_discount.setText((TicketsStock.discount * 10).toString())
        tv_total_amount.setText(TicketsStock.totalAmount.toString())
    }
}