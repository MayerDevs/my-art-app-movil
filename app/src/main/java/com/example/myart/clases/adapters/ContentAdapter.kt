package com.example.myart.clases.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.data.Content
import com.bumptech.glide.Glide

class ContentAdapter(private val context: Context, private val imageList: List<String>) : RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_content, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val imageUrl = imageList[position]

        // Cargar la imagen utilizando Glide
        Glide.with(context)
            .load(imageUrl)
            .into(holder.imageView)

        holder.like.setOnClickListener {
            Log.d("like", "like seleccted a la imagen ${imageUrl}")

        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_content_image)
        val like: ImageView = itemView.findViewById(R.id.iv_like)
    }
}