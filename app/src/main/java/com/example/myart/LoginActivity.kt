package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myart.clases.DbHelper
import com.example.myart.clases.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var register: TextView
    lateinit var login: Button
    lateinit var cor_usu: EditText
    lateinit var con_usu: EditText
    lateinit var DbHelper: DbHelper
    private val auth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = findViewById(R.id.btn_login)
        register = findViewById(R.id.tv_return_register)
        cor_usu = findViewById(R.id.et_user)
        con_usu = findViewById(R.id.et_pass)

        login.setOnClickListener{
            //obtener texto
            val cor_usu = cor_usu.text.toString()
            val con_usu = con_usu.text.toString()

            if (cor_usu.isEmpty() || con_usu.isEmpty()){
                Toast.makeText(this@LoginActivity, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(cor_usu,con_usu)
                    .addOnSuccessListener {
                        var DbHelper: DbHelper
                        DbHelper= DbHelper(this)
                        DbHelper.add(cor_usu,con_usu)
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "User or password wrong", Toast.LENGTH_SHORT).show()


                    }

            }

        }

        register.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}