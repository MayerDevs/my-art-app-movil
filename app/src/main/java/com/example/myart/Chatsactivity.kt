package com.example.myart

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myart.clases.adapters.ContactAdapter
import com.example.myart.clases.DbHelper
import com.example.myart.data.Contact
import org.json.JSONException
import org.json.JSONObject

class Chatsactivity : AppCompatActivity() {
    var DbHelper= DbHelper(this)
    lateinit var rv: RecyclerView
    lateinit var contactAdapter: ContactAdapter
    lateinit var dataList: ArrayList<Contact>
    var ide_usu:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        rv = findViewById(R.id.rv)
        dataList = ArrayList()

    }





    }