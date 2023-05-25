package com.example.myart.clases.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.data.Content
import com.bumptech.glide.Glide

class ContentAdapter(private val context: Context, private val contents: List<Content>) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contents[position]
        Log.d("URL", content.image)

        // Cargar la imagen utilizando Glide
        Glide.with(context)
            .load(content.image) // Reemplaza "imageUrl" por el nombre del campo de la imagen en tu clase Content
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_content_image)
    }

}