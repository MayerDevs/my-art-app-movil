package com.example.myart

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Multimedia {

    //var ide_con: Serial
    var ide_usu: Int = 0
    var tip_con: String = ""
    var txt_con: String = ""
    var ide_lik: Int = 0
    var con_con: String = ""

    var consulta:String=""

    lateinit var context: Context

    //constructor(ape_usu:String,tip_usu:String,cor_usu:String,eda_usu:String,cel_usu:Int,con_usu:String,context:Context,consulta:String,usu_usu:String){

    //constructor(ide_usu: Int, tip_con: String, txt_con: String, ide_lik: Int, con_con: String, context:Context){

    constructor(ide_usu: Int, tip_con: String, txt_con: String, ide_lik: Int, con_con: String) {

        //var ide_con: Serial
        this.ide_usu = ide_usu
        this.tip_con = tip_con
        this.txt_con = txt_con
        this.ide_lik = ide_lik
        this.con_con = con_con

        //this.consulta=consulta
    }

    fun Add(URL: String){
        val params = HashMap<String, Any>()
        //params.put("ide_usu", ide_usu.toString())
        params.put("ide_usu", ide_usu.toString())
        params.put("tip_con", tip_con)
        params.put("txt_con", txt_con)
        params.put("ide_lik", ide_lik.toString())
        params.put("con_con", con_con)

        //params.put("consulta",consulta)

        // val datos=JSONObject(params)
        val postRequest = object: StringRequest(Request.Method.POST,URL,
            Response.Listener<String> { response ->
                Toast.makeText(context, "succesful add", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
            }){
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                //params.put("ide_usu", ide_usu.toString())
                params.put("ide_usu", ide_usu.toString())
                params.put("tip_con", tip_con)
                params.put("txt_con", txt_con)
                params.put("ide_lik", ide_lik.toString())
                params.put("con_con", con_con)

                //params.put("consulta",consulta)

                // val datos=JSONObject(params)
                return params
            }
        }
        val queue = Volley.newRequestQueue(context)
        queue.add(postRequest)
    }
}