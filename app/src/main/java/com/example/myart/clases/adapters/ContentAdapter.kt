package com.example.myart.clases.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.R
import com.bumptech.glide.Glide
import com.example.myart.CommentActivity
import com.example.myart.data.Content
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ContentAdapter(private val context: Context, private val dataset: List<Content>) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    private val auth = FirebaseAuth.getInstance()
    private  val db = FirebaseFirestore.getInstance()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = itemView.findViewById(R.id.iv_content_image)
        val like: ImageView = itemView.findViewById(R.id.iv_like)
        val share: ImageView = itemView.findViewById(R.id.iv_share)
        val description: TextView = itemView.findViewById(R.id.tv_comment)
        val user_image: ImageView = itemView.findViewById(R.id.iv_user_image)
        val count_likes: TextView = itemView.findViewById(R.id.tv_count_likes)
        val comment: ImageView = itemView.findViewById(R.id.iv_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_content, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = dataset[position]
        val post = dataset[position]

        // Cargar la imagen utilizando Glide
        Glide.with(context)
            .load(post.con_con)
            .into(holder.image)

        Glide.with(context)
            .load(post.con_con)
            .into(holder.user_image)

        holder.description.text = post.txt_con


        val likes = post.lik_con!!.toMutableList()
        var liked = likes.contains(auth.uid)
        setColor(liked, holder.like)

        holder.like.setOnClickListener {
            liked = !liked
            setColor(liked, holder.like)

            if(liked) likes.add(auth.uid!!)
            else likes.remove(auth.uid)

            val doc = db.collection("Content").document(post.uid!!)
            db.runTransaction {
                it.update(doc, "lik_con", likes)

                null}
        }
        holder.count_likes.text = "${likes.size}"

        holder.share.setOnClickListener {
            Log.e("share", "entro")
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, post.txt_con)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }

        holder.comment.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, CommentActivity::class.java)
            context.startActivity(intent)
        }

    }

    private fun setColor(liked: Boolean, likeButton: ImageView){
        if (liked) likeButton.setColorFilter(R.color.like)
        else likeButton.setColorFilter(R.color.white)
    }



}