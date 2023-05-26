package com.example.myart

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myart.adapters.ContactAdapter
import com.example.myart.adapters.MessageAdapter
import com.example.myart.clases.DbHelper
import com.example.myart.data.Contact
import com.example.myart.data.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONException
import org.json.JSONObject

class Chatsactivity : AppCompatActivity() {
    var DbHelper= DbHelper(this)
    lateinit var rv: RecyclerView
    lateinit var btn_back: ImageButton
    lateinit var ContactAdapter: ContactAdapter
    lateinit var dataList: ArrayList<Contact>
    lateinit var dataList2: ArrayList<String>
    var aux=""
    private val auth= FirebaseAuth.getInstance()
    var ide_usu:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        rv = findViewById(R.id.rv)
        btn_back = findViewById(R.id.btn_back)

        dataList = ArrayList()
        dataList2 = ArrayList()
        DbHelper.addC("","","")
        val currentuser=auth.currentUser
        val bl = getIntent().getExtras();
        var uids:ArrayList<String> = bl!!.getSerializable("uids") as ArrayList<String>
        var tam=uids.size
        //Toast.makeText(this, ""+tam, Toast.LENGTH_SHORT).show()
        val uid=currentuser!!.uid
        val db= Firebase.firestore
        var count=0
        for(i in 0..uids.size-1){
            count=0
            aux=uids[i]
            for(j in i..uids.size-1){
                var com=uids[j]
                if(com==aux){
                    count=count+1
                }
            }
            if(count<=1){
                dataList2.add(aux)
            }
            //Toast.makeText(this, ""+count, Toast.LENGTH_SHORT).show()

        }
        for(i in 0..dataList2.size-1){

            db.collection("Usuarios").document(dataList2[i]).get().addOnSuccessListener {documento->
                var nom_usu=documento.data?.get("nom_usu").toString()
                var tip_usu=documento.data?.get("tip_usu").toString()
                var obj=Contact(dataList2[i],nom_usu,tip_usu)
                dataList.add(obj)
                //Toast.makeText(this, ""+nom_usu, Toast.LENGTH_SHORT).show()
                ContactAdapter=ContactAdapter(dataList)
                var layoutManager= LinearLayoutManager(applicationContext)
                rv.layoutManager=layoutManager
                rv.adapter=ContactAdapter
                ContactAdapter.notifyDataSetChanged()
                }

            }

        btn_back.setOnClickListener{
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }



        }



        }






