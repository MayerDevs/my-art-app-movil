package com.example.myart

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    private  val db = FirebaseFirestore.getInstance()

    lateinit var search: ImageView

    private lateinit var rv: RecyclerView
    private lateinit var contentAdapter: ContentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home = findViewById(R.id.iv_home)
        user = findViewById(R.id.iv_user)
        search = findViewById(R.id.iv_search)
        rv = findViewById(R.id.rv_content_container)

        // Realizar la consulta
        db.collection("Contenido").addSnapshotListener{value, error ->
            val posts = value!!.toObjects(Content::class.java)

            posts.forEachIndexed { index, post ->
              //  post.uid = value.documents[index].id


                rv.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ContentAdapter(this@MainActivity, posts)
                }
            }
        }

        home.setOnClickListener{
            Toast.makeText(this, "Home.", Toast.LENGTH_SHORT).show()
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



        search.setOnClickListener{
            Toast.makeText(this, "open camera.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
        }
    }

}