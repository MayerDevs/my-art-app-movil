package com.example.myart.clases

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Comentario {
    var ide_usu:Int
    var con_com:String=""
    var ide_con:Int
    constructor(ide_usu:Int,con_com:String,ide_con:Int){

        this.ide_usu=ide_usu
        this.con_com=con_com
        this.ide_con=ide_con



    }




}