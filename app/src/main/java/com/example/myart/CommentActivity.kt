package com.example.myart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.clases.adapters.CommentAdapter
import com.example.myart.data.Comment

class CommentActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var commentAdapter: CommentAdapter
    lateinit var dataList: ArrayList<Comment>

    var counter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        rv = findViewById(R.id.rv)
        dataList = ArrayList()

        dataList.add(Comment("Pepito", "BUENA IMAGEN"))
        dataList.add(Comment("Carlitos", "saludame"))
        dataList.add(Comment("Juanito", "hola"))
        dataList.add(Comment("Maria", "Cuanto vale ese carrito 👍"))



        commentAdapter = CommentAdapter(dataList)
        var layoutManager = LinearLayoutManager(applicationContext)
        rv.layoutManager = layoutManager
        rv.adapter = commentAdapter
        commentAdapter.notifyDataSetChanged()
    }
}