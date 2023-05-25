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
    private val auth= FirebaseAuth.getInstance()

    lateinit var search: ImageView

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

        val recyclerView: RecyclerView = findViewById(R.id.rv_content_container)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Content")

        // Realiza la consulta para obtener los datos
        collectionRef.get()
            .addOnSuccessListener { querySnapshot ->
                val contents = mutableListOf<Content>()

                // Itera sobre los documentos obtenidos en la consulta
                for (document in querySnapshot) {
                    // Obtén los valores de los campos del documento
                    val URL_image = document.getString("con_con").toString()
                    // ... obtén otros campos según tus necesidades
                    Log.e("mens", "route ${URL_image}")

                    // Crea una instancia de la clase Content con los valores obtenidos
                    val content = Content(URL_image)
                    contents.add(content)
                }
                Log.e("mens", "Se hizo la busqueda")
                // Crea el adaptador con la lista de contenidos obtenidos
                val adapter = ContentAdapter(this, contents)
                recyclerView.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.e("mens", "Error al obtener los datos: $exception")
            }


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