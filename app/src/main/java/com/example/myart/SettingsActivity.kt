package com.example.myart

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.myart.clases.DbHelper
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {
    lateinit var previous: ImageView
    lateinit var close: Button
    lateinit var delete: Button
    lateinit var update: Button
    var DbHelper= DbHelper(this)
    private val auth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        delete = findViewById(R.id.btn_delete_account)
        previous = findViewById(R.id.iv_previous)
        close = findViewById(R.id.btn_close_session)
        update = findViewById(R.id.btn_update)

        previous.setOnClickListener{
            Toast.makeText(this, "previous.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
        close.setOnClickListener{
            auth.signOut()
            Toast.makeText(this, "Close session succesfully.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)


        }
        update.setOnClickListener{
            val i = Intent(this, RegisterActivity::class.java)
            i.putExtra("log",true)
            startActivity(i)
        }

        delete.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            i.putExtra("log",true)
            startActivity(i)

        }
    }
}