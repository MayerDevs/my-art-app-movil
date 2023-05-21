package com.example.myart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.data.Contact
import com.example.myart.R

public class ContactAdapter(private var itemList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tv_name)
        var tvEmail: TextView = view.findViewById(R.id.tv_email)
        var tvDate: TextView = view.findViewById(R.id.tv_date)
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
        holder.tvName.text = item.name
        holder.tvEmail.text = item.email
        holder.tvDate.text = item.date
    }


}