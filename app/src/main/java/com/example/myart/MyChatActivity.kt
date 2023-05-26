
package com.example.myart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.adapters.MessageAdapter
import com.example.myart.data.Message
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyChatActivity : AppCompatActivity() {

    lateinit var men_con: EditText
    lateinit var Send: ImageView
    var ide_usu: String = ""
    lateinit var rv: RecyclerView
    lateinit var dataList: ArrayList<Message>
    lateinit var messageAdapter: MessageAdapter

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_chat)

        val bl = intent.extras
        val uid = bl?.getString("uid").toString()
        Send = findViewById(R.id.send)
        men_con = findViewById(R.id.men_con)
        ide_usu = uid
        dataList = ArrayList()
        rv = findViewById(R.id.rv2)

        if (uid != null) {
            initViews(uid)
        }

        Send.setOnClickListener {
            val messageContent = men_con.text.toString().trim()
            val auth = FirebaseAuth.getInstance()

            if (messageContent.isNotEmpty()) {
                val currentTimestamp = Timestamp.now()
                val message = Message(auth.currentUser!!.uid, ide_usu, messageContent, currentTimestamp)
                db.collection("Mensajes")
                    .add(message)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()
                        men_con.text.clear()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error al enviar el mensaje", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun initViews(uid: String) {
        val auth = FirebaseAuth.getInstance()

        db.collection("Mensajes")
            .whereIn("ide_usu", listOf(uid, auth.currentUser!!.uid))
            .orderBy("dat_men", Query.Direction.ASCENDING)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val uniqueDataList = HashSet<Message>()

                for (doc in value!!) {
                    val ide_usu = doc.getString("ide_usu").toString()
                    val ide_rec = doc.getString("ide_rec").toString()
                    val men_con = doc.getString("men_con").toString()
                    val dat_men: com.google.firebase.Timestamp? = doc.getTimestamp("dat_men")
                    val message = Message(ide_usu, ide_rec, men_con, dat_men)

                    uniqueDataList.add(message)
                }

                dataList.clear()
                dataList.addAll(uniqueDataList)
                dataList.sortBy { it.dat_men }

                messageAdapter = MessageAdapter(dataList)
                rv.layoutManager = LinearLayoutManager(applicationContext)
                rv.adapter = messageAdapter
                messageAdapter.notifyDataSetChanged()

                // Scroll hasta la última posición del RecyclerView
                rv.scrollToPosition(dataList.size - 1)
            }
    }
}

//Este código corrige algunos problemas en el manejo de la lista de mensajes y el adaptador, además de mostrar tanto los mensajes enviados como los recibidos en tiempo real.










