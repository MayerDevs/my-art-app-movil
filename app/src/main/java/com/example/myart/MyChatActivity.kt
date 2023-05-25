package com.example.myart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.adapters.MessageAdapter
import com.example.myart.clases.DbHelper
import com.example.myart.data.Message
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyChatActivity : AppCompatActivity() {

    lateinit var men_con: EditText
    lateinit var Send: FloatingActionButton
    var ide_usu:String=""
    var DbHelper= DbHelper(this)
    private var chatId = ""
    private var user = ""
    lateinit var rv: RecyclerView
    lateinit var dataList: ArrayList<Message>
    lateinit var MessageAdapter: MessageAdapter

    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_chat)

        val bl = getIntent().getExtras();
       var uid = bl?.getString("uid").toString()
      Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show()
        dataList= ArrayList()
        rv = findViewById(R.id.rv2)

        if(uid!=null) {
            initViews(uid)
        }
    }

    private fun initViews(uid:String){

        db.collection("Mensajes")
            .whereEqualTo("ide_usu", uid)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                for (doc in value!!.documentChanges!!) {
                    for(documentos in value){
                        var ide_usu=documentos["ide_usu"].toString()
                        var ide_rec=documentos["ide_rec"].toString()
                        var men_con=documentos["men_con"].toString()
                        var dat_men: com.google.firebase.Timestamp? = documentos.getTimestamp("dat_men")
                        var men=Message(ide_usu,ide_rec,men_con, dat_men )
                        dataList.add(men)
                    }
                }

                MessageAdapter= MessageAdapter(dataList)
                var layoutManager= LinearLayoutManager(applicationContext)
                rv.layoutManager=layoutManager
                rv.adapter=MessageAdapter
                MessageAdapter.notifyDataSetChanged()
            }



        }





    }




