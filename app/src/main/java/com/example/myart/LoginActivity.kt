package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myart.clases.Usuario

class LoginActivity : AppCompatActivity() {

    lateinit var register: TextView
    lateinit var login: Button
    lateinit var cor_usu: EditText
    lateinit var con_usu: EditText

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
               // Toast.makeText(this@LoginActivity, "Login succesfully.", Toast.LENGTH_SHORT).show()
                val user= Usuario("","","",cor_usu,"",0,con_usu,this,"login","")
                user.Login("http://192.168.80.18/MyArt/Usuario.php")
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)

            }

        }

        register.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}