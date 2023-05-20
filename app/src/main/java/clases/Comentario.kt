package clases

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Comentario {
    var ide_com:Int=0
    var ide_usu:Int
    var con_com:String=""
    var ide_con:Int
    lateinit var context: Context
    var consulta:String=""
    constructor(ide_usu:Int,con_com:String,ide_con:Int,context:Context,consulta:String){

        this.ide_usu=ide_usu
        this.con_com=con_com
        this.ide_con=ide_con
        this.context=context
        this.consulta=consulta


    }

    fun Register (URL: String){
        val jsonRequest= JsonObjectRequest(Request.Method.GET,URL,null, { response ->
            ide_com=response.getInt("ide_com")
            ide_con=response.getInt("ide_con")
            con_com=response.getString("con_com")
            var DbHelper: DbHelper
            DbHelper= DbHelper(context)
            DbHelper.addC(ide_com,con_com,ide_con)
            Toast.makeText(context, "account delete succesfully", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
        })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonRequest)

    }
    fun delete (){
        var URL="http://192.168.80.18/MyArt/Comentarios.php?ide_com=$ide_com&consulta=delete"
        consult(URL)

    }
    fun update (){
        var URL="http://192.168.80.18/MyArt/Comentarios.php?com_com=$con_com&ide_com=$ide_com&consulta=update"
        consult(URL)
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