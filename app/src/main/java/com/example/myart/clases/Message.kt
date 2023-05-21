package com.example.myart.clases

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Message {
    var ide_usu: Int=0
    var nom_usu: String=""
    var men_con: String=""
    var consulta: String=""
    lateinit var context: Context
    var ide_rec: Int=0
    constructor(ide_usu:Int, nom_usu:String, men_con:String,ide_rec:Int, context: Context, consulta:String){

        this.ide_usu=ide_usu
        this.nom_usu=nom_usu
        this.men_con=men_con
        this.ide_rec=ide_rec
        this.context=context
        this.consulta=consulta


    }
    fun Send (URL: String) {
        val send = StringRequest(Request.Method.GET,URL,
            { response ->
                this.consult("http://192.168.80.18/MyArt/Mensaje.php?consulta=insert,ide_usu=$ide_usu,men_con=$men_con,ide_rec=$ide_rec")
               // Toast.makeText(context, "Succesfully petition", Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(send)

    }
    fun consult (URL: String){
        val Delete = StringRequest(Request.Method.GET,URL,
            { response ->
                Toast.makeText(context, "Succesfully petition", Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(Delete)

    }


    }