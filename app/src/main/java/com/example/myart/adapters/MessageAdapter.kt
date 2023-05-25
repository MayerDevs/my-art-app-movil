package com.example.myart.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.data.Message
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

public class MessageAdapter(private var itemList: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var men_con: TextView = view.findViewById(R.id.men_con)
        var nom_usu: TextView = view.findViewById(R.id.tv_contacto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.card_message, parent, false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = itemList[position]
        val db= Firebase.firestore
        db.collection("Usuarios").document(item.ide_usu).get().addOnSuccessListener { documento ->
            holder.men_con.text = item.men_con
            holder.nom_usu.text=documento.data!!.get("nom_usu").toString()
        }
    }


}