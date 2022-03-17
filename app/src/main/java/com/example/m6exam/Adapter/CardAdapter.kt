package com.example.m6exam.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m6exam.Model.CardItem
import com.example.m6exam.R

class CardAdapter (var items:ArrayList<CardItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickitemhome:((word:String)-> Unit) ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = items[position]

        if (holder is CardViewHolder) {
            val tv_cardNumber = holder.tv_cardNumber
            val tv_cardHolder = holder.tv_cardHolder
            val tv_cardDate = holder.tv_cardDate

            tv_cardNumber.text = card.cardNumber
            tv_cardHolder.text = card.fullname
            tv_cardDate.text = card.expireDate
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("CutPasteId")
    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        var tv_cardNumber: TextView
        var tv_cardHolder: TextView
        var tv_cardDate: TextView

        init {
            tv_cardNumber = view.findViewById(R.id.tv_cardNumber)
            tv_cardHolder = view.findViewById(R.id.tv_cardHolder)
            tv_cardDate = view.findViewById(R.id.tv_cardDate)
        }
    }
}