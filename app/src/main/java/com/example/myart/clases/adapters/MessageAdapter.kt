package com.example.myart.clases.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_message, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        val db = Firebase.firestore
        db.collection("Usuarios").document(item.ide_usu).get().addOnSuccessListener { documento ->
            holder.men_con.text = item.men_con
            holder.nom_usu.text = documento.data?.get("nom_usu").toString()

            val currentUserId = "current_user_id" // Reemplaza "current_user_id" con el identificador del usuario actual

            // Verificar si el mensaje pertenece al usuario actual
            val isCurrentUserMessage = item.ide_usu == currentUserId

            // Configurar el estilo del mensaje según si pertenece al usuario actual
            if (isCurrentUserMessage) {
                // Estilo para mensajes del usuario actual
                holder.men_con.setBackgroundResource(R.drawable.chat_message_background_user)
                // Otras configuraciones adicionales según tus necesidades
            } else {
                // Estilo para otros mensajes
                holder.men_con.setBackgroundResource(R.drawable.chat_message_background_other)
                // Otras configuraciones adicionales según tus necesidades
            }
        }
    }


}