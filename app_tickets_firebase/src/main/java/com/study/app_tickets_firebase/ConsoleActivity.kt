package com.study.app_tickets_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_console.*
import kotlinx.android.synthetic.main.activity_order_list.*

class ConsoleActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        context = this
        title ="雲端購票 - 後台"
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    when(it.key.toString()) {
                        "discount" -> et_discount.setText(it.value.toString())
                        "price" -> et_price.setText(it.value.toString())
                        "totalAmount" -> et_total_amount.setText(it.value.toString())
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun update(view: View) {
        val tag = view.tag.toString()
        var value = 0.0
        when(tag) {
            "discount" -> value = et_discount.text.toString().toDouble()
            "price"    -> value = et_price.text.toString().toDouble()
            "totalAmount" -> value = et_total_amount.text.toString().toDouble()
        }
        myRef.child(tag).setValue(value)
        Toast.makeText(context, tag + " 修改成功", Toast.LENGTH_SHORT).show()
    }
}