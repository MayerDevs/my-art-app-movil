package com.example.myart

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myart.clases.DbHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyChatActivity : AppCompatActivity() {

    lateinit var men_con: EditText
    lateinit var Send: FloatingActionButton
    lateinit var ide_usu:String
    var DbHelper= DbHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_chat)
        men_con=findViewById(R.id.men_con)
        Send=findViewById(R.id.send)


        Send.setOnClickListener{
        }

    }

}