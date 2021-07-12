package com.study.app_tickets_firebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_console.*
import kotlinx.android.synthetic.main.activity_order_list.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.charset.Charset

class ConsoleActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("ticketsStock")
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_console)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        context = this
        title = "雲端購票 - 後台"
        // Read from the database
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                // 統計資料累計
                var sumAllTickets = 0
                var sumOneWay = 0
                var sumRoundTrip = 0
                var sumTotal = 0
                // 個別訂購人的統計資料列表
                // [{"Anita": 10000}, {"Helen": 8000}, {"John":50000} ...]
                var statListByUser = mutableListOf<Map<String, Int>>()

                children.forEach {
                    when(it.key.toString()) {
                        "discount" -> et_discount.setText(it.value.toString())
                        "price" -> et_price.setText(it.value.toString())
                        "totalAmount" -> et_total_amount.setText(it.value.toString())
                        // 訂單明細
                        "orders" -> {
                            it.children.forEach { // 訂購人
                                // 取得當前訂購人的訂購總金額
                                var mapUser = mutableMapOf<String, Int>()
                                // 當前訂購人姓名
                                val mapUserName = it.key.toString()
                                // 預設訂購總金額 = 0
                                mapUser.put(mapUserName, 0)
                                it.children.forEach { // 訂票日期
                                    it.children.forEach { // 訂票內容
                                        //Log.d("MainActivity", it.key.toString())
                                        when(it.key.toString()) { // 項目
                                            "allTickets" -> sumAllTickets += it.value.toString().toInt()
                                            "oneWay" -> sumOneWay += it.value.toString().toInt()
                                            "roundTrip" -> sumRoundTrip += it.value.toString().toInt()
                                            "total" -> {
                                                val total = it.value.toString().toInt()
                                                sumTotal += total
                                                // 累計當前訂購人的訂購總金額
                                                mapUser.put(mapUserName, mapUser.get(mapUserName)!! + total)
                                            }
                                        }
                                    }
                                }
                                // 將訂購人訂購總金額放入「個別訂購人的統計資料列表」
                                statListByUser.add(mapUser)
                            }
                        }
                    }
                }

                // 顯示統計資料
                tv_stat.text = "總賣票數：${String.format("%,d", sumAllTickets)} 張\n" +
                        "總單程票：${String.format("%,d", sumOneWay)} 張\n" +
                        "總來回票：${String.format("%,d", sumRoundTrip * 2)} 張 （${String.format("%,d", sumRoundTrip)} 組）\n" +
                        "總銷售金額：$${String.format("%,d", sumTotal)} 元"

                Log.d("ConsoleActivity", statListByUser.toString())
                // 載入圖表
                loadChart(statListByUser)

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun loadChart(statListByUser: List<Map<String, Int>>) {
        // ['Anita', 12480], ...
        var rowDataByChart: String = ""
        statListByUser.forEach {
            val key = it.keys.iterator().next()
            val value = it[key]
            rowDataByChart += "['$key', $value],"
        }
        Log.d("ConsoleActivity", rowDataByChart)

        var webSettings =  web_view.settings;
        webSettings.setJavaScriptEnabled(true); // 啟用 Javascript
        webSettings.setBuiltInZoomControls(true); // 啟用 Zoom
        var asset_path = "file:///android_asset/";
        var html = getHtml("chart.html");
        html = String.format(html!!, rowDataByChart)
        web_view.loadDataWithBaseURL(asset_path, html!!, "text/html", "utf-8", null);
        web_view.requestFocusFromTouch();
    }

    // 取得 html 內容字串
    private fun getHtml(filename: String): String? {
        var html: String? = null
        try {
            val `in`: InputStream = assets.open(filename)
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(`in`.available())
            `in`.read(buffer) // 讀出
            out.write(buffer) // 寫入
            html = String(out.toByteArray(), Charset.forName("UTF-8"))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return html
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 1, 1, 10, "訂單細目" -> group id, item id, order id, 名稱
        menu?.add(0, 1, 10, "訂單細目")?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            1 -> { // "訂單細目"
                val intent = Intent(context, OrderListActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}