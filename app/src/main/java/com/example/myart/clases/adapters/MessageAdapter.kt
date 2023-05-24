package com.example.myart.clases.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.clases.Message

public class MessageAdapter(private var itemList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nom_usu: TextView = view.findViewById(R.id.tv_contacto)
        var men_con: TextView = view.findViewById(R.id.tv_mensaje)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_contact, parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = itemList[position]
        holder.nom_usu.text =item.nom_usu
        holder.men_con.text = item.men_con
    }


}