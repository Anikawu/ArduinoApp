package com.example.arduinoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Arduino 物聯網 App")

        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()  //讀取資料
                findViewById<TextView>(R.id.tv_text).text = value
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}