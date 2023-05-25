package com.example.myart.clases.adapters

<<<<<<< HEAD
import android.content.Context
import android.view.LayoutInflater
=======
>>>>>>> a0ae4ebc75bc2510b2994c245b7368a1973aa2e3
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.example.myart.data.Content
<<<<<<< HEAD
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
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_content_image)
=======
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
>>>>>>> a0ae4ebc75bc2510b2994c245b7368a1973aa2e3
    }
}