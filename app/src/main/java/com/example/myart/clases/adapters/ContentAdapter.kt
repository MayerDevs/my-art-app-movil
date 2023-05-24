package com.example.myart.clases.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.data.Content
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class ContentAdapter(private val contentList: List<Content>) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ...

        fun bind(content: Content) {
            // Configurar la imagen en la ImageView
            val ivContentImage: ImageView = itemView.findViewById(R.id.iv_content_image)
            // Aquí debes cargar la imagen en la ImageView usando la biblioteca o método que estés utilizando para cargar imágenes desde la URL o el recurso local.
            // Por ejemplo, puedes usar Picasso, Glide o cargar la imagen manualmente desde una URL o un recurso local.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_content, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contentList[position]
        holder.bind(content)
    }
}