package com.example.myart

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    lateinit var home: ImageView
    lateinit var settings: ImageView
    lateinit var user: TextView
    lateinit var seguir: Button
    lateinit var chat: Button
    lateinit var chats: Button
    lateinit var gallery: ImageView
    var uid=""
    private val auth= FirebaseAuth.getInstance()
    lateinit var dataList2: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        home = findViewById(R.id.iv_home)
        settings = findViewById(R.id.iv_settings)
        user = findViewById(R.id.txt_usu)
        chat = findViewById(R.id.btn_chat)
        seguir = findViewById(R.id.btn_seguir)
        chats = findViewById(R.id.btn_chats)
        gallery = findViewById(R.id.iv_gallery_image)


        dataList2 = ArrayList()
        val bl = getIntent().getExtras()
        var ide_usu= bl?.getString("ide_usu")
        val currentuser = auth.currentUser
        if(ide_usu!=null){
            uid=ide_usu
            if (uid != currentuser?.uid) {
                chats.visibility = View.GONE
            }
            else{
                chat.visibility = View.GONE
                seguir.visibility = View.GONE
            }
        }
        else{
            chat.visibility = View.GONE
            seguir.visibility = View.GONE
            uid = currentuser!!.uid

        }
        val db= Firebase.firestore
        db.collection("Usuarios").document(uid).get().addOnSuccessListener {documento->
            if(documento.exists()){
                val nom_usu=documento.data?.get("nom_usu").toString()
                user.setText(nom_usu)
            }
        }

        db.collection("Usuarios").document(uid).get().addOnSuccessListener {documento->
            val con_con = documento.data?.get("con_con")
            Log.e("usuario", "el codigo del usuario es ${uid} y la imagen es ${con_con}")
            val image = findViewById<ImageView>(R.id.iv_image_profile)
            Glide.with(this)
                .load(con_con)
                .into(image)
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
            i.putExtra("uid",uid)
            startActivity(i)
        }
        chats.setOnClickListener{
            val db= Firebase.firestore
            //  Toast.makeText(this, ""+uid, Toast.LENGTH_SHORT).show()
            db.collection("Mensajes").whereEqualTo("ide_rec",uid).get().addOnSuccessListener { documents ->
                for(documento in documents) {
                    var uid=documento["ide_usu"]
                    dataList2.add(uid.toString())
                }
                val i = Intent(this, Chatsactivity::class.java)
                i.putExtra("uids",dataList2)
                startActivity(i)
            }
            //Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show()
        }
        gallery.setOnClickListener{
            Log.d("gallery", "GALLERY OPEN")

            val i = Intent(this, GalleryProfileActivity::class.java)
            startActivity(i)
        }
    }
}