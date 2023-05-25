package com.example.myart

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.clases.DbHelper
import com.example.myart.clases.adapters.ContentAdapter
import com.example.myart.data.Content
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var home: ImageView
    lateinit var video: ImageView
    lateinit var image: ImageView
    lateinit var music: ImageView
    lateinit var user: ImageView
    lateinit var upload_resource: ImageView
    private val auth = FirebaseAuth.getInstance()

    lateinit var search: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var contentAdapter: ContentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home = findViewById(R.id.iv_home)
        video = findViewById(R.id.iv_video)
        image = findViewById(R.id.iv_image)
        music = findViewById(R.id.iv_music)
        user = findViewById(R.id.iv_user)
        upload_resource = findViewById(R.id.iv_upload_resource)
        search = findViewById(R.id.iv_search)



        val db = FirebaseFirestore.getInstance()

        // Realizar la consulta
        db.collection("Contenido")
            .get()
            .addOnSuccessListener { documents ->
                // El método onSuccess se ejecuta cuando la consulta es exitosa
                // Aquí puedes obtener los documentos y extraer las imágenes

                // Crear una lista para almacenar las imágenes
                val imageList = mutableListOf<String>()

                // Iterar sobre los documentos
                for (document in documents) {
                    // Obtener el campo "con_con" de cada documento
                    val imageUrl = document.getString("con_con")

                    // Agregar la URL de la imagen a la lista
                    imageUrl?.let {
                        (imageList as MutableList<String>).add(it)
                    }
                }
                contentAdapter = ContentAdapter(this, imageList)
                recyclerView.adapter = contentAdapter
            }
            .addOnFailureListener { exception ->
                // El método onFailure se ejecuta si ocurre algún error durante la consulta
                // Aquí puedes manejar el error de acuerdo a tus necesidades
            }

        recyclerView = findViewById(R.id.rv_content_container)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtén la lista de imágenes (imageList) de tu consulta a Firebase
        // Asumiendo que tienes la lista de imágenes disponible




        home.setOnClickListener{
            Toast.makeText(this, "Home.", Toast.LENGTH_SHORT).show()
        }
        video.setOnClickListener{
            Toast.makeText(this, "video.", Toast.LENGTH_SHORT).show()
        }
        image.setOnClickListener{
            Toast.makeText(this, "image.", Toast.LENGTH_SHORT).show()
        }
        music.setOnClickListener{
            Toast.makeText(this, "music.", Toast.LENGTH_SHORT).show()
        }
        user.setOnClickListener{
            if(auth.currentUser!=null){
                val i = Intent(this, ProfileActivity::class.java)
                startActivity(i)
            }
            else{
                Toast.makeText(this, "user.", Toast.LENGTH_SHORT).show()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            }
        }

        upload_resource.setOnClickListener{
            Toast.makeText(this, "camera.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
        }

        search.setOnClickListener{
            Toast.makeText(this, "search.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, SearchActivity::class.java)
            startActivity(i)
        }
    }

}