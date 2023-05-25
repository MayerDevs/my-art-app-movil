package com.example.myart.clases.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.MyChatActivity
import com.example.myart.data.Contact
import com.example.myart.R
import com.example.myart.RegisterNextActivity

public class ContactAdapter(private var itemList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvName: TextView = view.findViewById(R.id.tv_name)
        var tvEmail: TextView = view.findViewById(R.id.tv_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_contact, parent, false)
        itemView.setOnClickListener{
            val i = Intent(parent.context, MyChatActivity::class.java)
            startActivity(parent.context,i,null)
        }
        return MyViewHolder(itemView)


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = itemList[position]
        holder.tvName.text = item.nom_usu
        holder.tvEmail.text = item.cor_usu
    }


}