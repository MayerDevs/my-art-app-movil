package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    lateinit var home: ImageView
    lateinit var settings: ImageView
    lateinit var user: TextView
    lateinit var chat: Button
    lateinit var chats: Button
    private val auth= FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        home = findViewById(R.id.iv_home)
        settings = findViewById(R.id.iv_settings)
        user = findViewById(R.id.txt_usu)
        chat = findViewById(R.id.btn_chat)
        chats = findViewById(R.id.btn_chats)
        val bl = getIntent().getExtras();

        val currentuser=auth.currentUser
        val uid=currentuser!!.uid
        val db= Firebase.firestore
        db.collection("Usuarios").document(uid).get().addOnSuccessListener {documento->
            if(documento.exists()){
                val nom_usu=documento.data?.get("nom_usu").toString()
                user.setText(nom_usu)

            }
        }

        home.setOnClickListener{
            Toast.makeText(this, "home.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        settings.setOnClickListener{
            Toast.makeText(this, "home.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, SettingsActivity::class.java)
            startActivity(i)
        }
        chat.setOnClickListener{
            Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MyChatActivity::class.java)
            startActivity(i)
        }
        chats.setOnClickListener{
            Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
            val i = Intent(this, Chatsactivity::class.java)
            startActivity(i)
        }

    }
}