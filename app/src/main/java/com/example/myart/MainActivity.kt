package com.example.myart

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myart.clases.DbHelper
<<<<<<< HEAD
=======
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myart.clases.Usuario
>>>>>>> a0ae4ebc75bc2510b2994c245b7368a1973aa2e3
import com.example.myart.clases.adapters.ContentAdapter
import com.example.myart.data.Content
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

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
<<<<<<< HEAD
        upload_resource = findViewById(R.id.iv_camera)
=======
        upload_resource = findViewById(R.id.iv_upload_resource)

        val contenidoList = intent.getSerializableExtra("contenidoList") as? ArrayList<Content>
        if (contenidoList != null) {
            val recyclerView: RecyclerView = findViewById(R.id.rv_content_container)
            val layoutManager = LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager

            val adapter = ContentAdapter(contenidoList)
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(this, "error.", Toast.LENGTH_SHORT).show()
            // Tratar el caso de lista nula
            // Por ejemplo, mostrar un mensaje de error o realizar alguna otra acción
        }

        /* VARIABLES BLOQUEADAS PORQUE SE ENCUENTRAN EN EL CARD_VIDEO

        image_user_resource = findViewById(R.id.iv_user_resource)
        like = findViewById(R.id.iv_like)
        comment = findViewById(R.id.iv_comment)
        share = findViewById(R.id.iv_share)
        */

>>>>>>> a0ae4ebc75bc2510b2994c245b7368a1973aa2e3
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
                Thread {
                    // Iterar sobre los documentos
                    for (document in documents) {
                        // Obtener el campo "con_con" de cada documento
                        val imageUrl = document.getString("con_con")

                        // Verificar si la imagen existe
                        imageUrl?.let {
                            try {
                                val url = URL(it)
                                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                                connection.requestMethod = "HEAD"

                                // Obtener el código de respuesta
                                val responseCode = connection.responseCode

                                if (responseCode == HttpURLConnection.HTTP_OK) {
                                    // La imagen existe, agregar la URL a la lista
                                    runOnUiThread {
                                        imageList.add(it)
                                    }
                                } else {
                                    // La imagen no existe, omitirla
                                    Log.e(TAG, "La imagen no existe: $it")
                                }

                                connection.disconnect()
                            } catch (e: IOException) {
                                // Error al verificar la existencia de la imagen
                                Log.e(TAG, "Error al verificar la existencia de la imagen: $it", e)
                            }
                        }
                    }

                    // Actualizar el RecyclerView en el hilo principal
                    runOnUiThread {
                        contentAdapter = ContentAdapter(this, imageList)
                        recyclerView.adapter = contentAdapter
                    }
                }.start()
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
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
        }
    }

}