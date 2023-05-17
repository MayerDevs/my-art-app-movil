package com.example.myart

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myart.clases.Usuario

class MainActivity : AppCompatActivity() {

    lateinit var home: ImageView
    lateinit var video: ImageView
    lateinit var image: ImageView
    lateinit var music: ImageView
    lateinit var user: ImageView
    lateinit var upload_resource: ImageView

    /* VARIABLES BLOQUEADAS PORQUE SE ENCUENTRAN EN EL CARD_VIDEO

    lateinit var image_user_resource: ImageView
    lateinit var like: ImageView
    lateinit var comment: ImageView
    lateinit var share: ImageView
     */

    lateinit var search: ImageView
    var log=false
    var DbHelper=DbHelper(this)
    //lateinit var comment_resource: ImageView
    //lateinit var name_user_resource: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home = findViewById(R.id.iv_home)
        video = findViewById(R.id.iv_video)
        image = findViewById(R.id.iv_image)
        music = findViewById(R.id.iv_music)
        user = findViewById(R.id.iv_user)
        upload_resource = findViewById(R.id.iv_upload_resource)

        /* VARIABLES BLOQUEADAS PORQUE SE ENCUENTRAN EN EL CARD_VIDEO

        image_user_resource = findViewById(R.id.iv_user_resource)
        like = findViewById(R.id.iv_like)
        comment = findViewById(R.id.iv_comment)
        share = findViewById(R.id.iv_share)
        */

        search = findViewById(R.id.iv_search)
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
            val db:SQLiteDatabase=DbHelper.readableDatabase
            val cursor=db.rawQuery("SELECT * FROM Usuarios",null)
            if(cursor.moveToFirst()){
                Toast.makeText(this, "Start session", Toast.LENGTH_SHORT).show()
                var cor_usu=cursor.getString(1)
             //   var user= Usuario("","","", cor_usu,"",0,"",this,"consult","")
                var URL="http://192.168.80.18/MyArt/Usuario.php?cor_usu=$cor_usu&consulta=consult"
               var nom_usu=""
                val jsonRequest= JsonObjectRequest(
                    Request.Method.GET,URL,null,
                    { response ->
                    nom_usu=response.getString("nom_usu")
                        val i = Intent(this, ProfileActivity::class.java)
                        i.putExtra("nom_usu",nom_usu)
                        startActivity(i)

                },
                    {
                    Toast.makeText(this, "Something went wrong with the consult.", Toast.LENGTH_SHORT).show()

                })
                val requestQueue = Volley.newRequestQueue(this)
                requestQueue.add(jsonRequest)
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

        /* VARIABLES BLOQUEADAS PORQUE SE ENCUENTRAN EN EL CARD_VIDEO

        image_user_resource.setOnClickListener{
            Toast.makeText(this, "profile user resource.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
        like.setOnClickListener{
            Toast.makeText(this, "like like.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        comment.setOnClickListener{
            Toast.makeText(this, "comment comment.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        share.setOnClickListener{
            Toast.makeText(this, "share share.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }*/
        search.setOnClickListener{
            Toast.makeText(this, "search.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, SearchActivity::class.java)
            startActivity(i)
        }
    }
}