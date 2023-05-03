package com.example.myart.clases

import android.content.Context
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley

open class Usuario {
   // var ide_usu:Int=0
    var nom_usu:String=""
    var ape_usu:String=""
    var tip_usu:String=""
    var cor_usu:String=""
    var eda_usu:String=""
    var cel_usu:Int=0
    var con_usu:String=""
    var usu_usu:String=""
    var consulta:String=""
    lateinit var context:Context

    constructor(nom_usu:String,ape_usu:String,tip_usu:String,cor_usu:String,eda_usu:String,cel_usu:Int,con_usu:String,context:Context,consulta:String,usu_usu:String){

       // this.ide_usu=ide_usu
        this.nom_usu=nom_usu
        this.ape_usu=ape_usu
        this.tip_usu=tip_usu
        this.cor_usu=cor_usu
        this.eda_usu=eda_usu
        this.cel_usu=cel_usu
        this.con_usu=con_usu
        this.context=context
        this.consulta=consulta
        this.usu_usu=usu_usu

    }

    fun executeService(URL: String){

        val postRequest = object :  StringRequest(Request.Method.POST,URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "succesful conection", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            })
        {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                //params.put("ide_usu", ide_usu.toString())
                params.put("nom_usu", nom_usu)
                params.put("ape_usu", ape_usu)
                params.put("tip_usu", tip_usu)
                params.put("cor_usu", cor_usu)
                params.put("eda_usu", eda_usu)
                params.put("cel_usu", cel_usu.toString())
                params.put("con_usu", con_usu)
                params.put("usu_usu", usu_usu)
                params.put("consulta", consulta)
                return params
            }

        }

        val queue = Volley.newRequestQueue(context)
        queue.add(postRequest)



    }




}