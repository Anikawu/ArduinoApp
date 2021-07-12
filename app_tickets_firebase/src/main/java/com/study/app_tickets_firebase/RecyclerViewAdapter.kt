package com.study.app_tickets_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.order.view.*

// 適配器(配置每一筆紀錄的擺放方式)
class RecyclerViewAdapter(val listener: RowOnItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var orderList: List<Order> = ArrayList<Order>()
    fun setOrders(orderList: List<Order>) {
        this.orderList = orderList
    }

    // View 配置方式
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userName   = view.tv_userName
        val allTickets = view.tv_allTickets
        val roundTrip  = view.tv_roundTrip
        val oneWay     = view.tv_oneWay
        val total      = view.tv_total
        fun bind(order: Order) {
            userName.text   = order.userName
            allTickets.text = order.allTickets.toString()
            roundTrip.text  = order.roundTrip.toString()
            oneWay.text     = order.oneWay.toString()
            total.text      = order.total.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(orderList[position])
        }
        holder.bind(orderList[position])
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    interface RowOnItemClickListener {
        fun onItemClickListener(order: Order)
    }
}