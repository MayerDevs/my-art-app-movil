package com.example.myart

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var home: ImageView
    lateinit var video: ImageView
    lateinit var image: ImageView
    lateinit var music: ImageView
    lateinit var user: ImageView
    lateinit var upload_resource: ImageView
    lateinit var image_user_resource: ImageView
    lateinit var like: ImageView
    lateinit var comment: ImageView
    lateinit var share: ImageView
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
        image_user_resource = findViewById(R.id.iv_user_resource)
        like = findViewById(R.id.iv_like)
        comment = findViewById(R.id.iv_comment)
        share = findViewById(R.id.iv_share)
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
            Toast.makeText(this, "upload resource.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
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
        }
        search.setOnClickListener{
            Toast.makeText(this, "search.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, SearchActivity::class.java)
            startActivity(i)
        }
    }
}