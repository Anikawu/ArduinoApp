package com.example.app_qrcode_scanner

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.os.PersistableBundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE =200   //對CAMERA請求參數200(可自定義)
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context=this

        if (checkPermission()){
            //正常執行程式
            runProgram()
        }else {
            //啟動動態核准對話框
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CODE)
        }


    }

    fun runProgram(){
        //正常執行程式
        title = "正常執行程序"
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_REQUEST_CODE){
              runProgram()
        }
    }


    //動態監測 使用者是否有同意使用該權限(ex: camera)
    private fun checkPermission(): Boolean {
        var check = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        val result = (check == PackageManager.PERMISSION_GRANTED)
        if(result){
            title = "Permission is ok"
            Toast.makeText(context,"Permission is ok",Toast.LENGTH_SHORT).show()
            return true
        }else{
            title = "Permission is not granted"
            Toast.makeText(context,"Permission is not granted",Toast.LENGTH_SHORT).show()
            return false
        }
    }
}