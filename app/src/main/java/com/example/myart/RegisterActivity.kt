package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myart.clases.Usuario

class RegisterActivity : AppCompatActivity() {

    lateinit var user_name: EditText
    lateinit var user_email: EditText
    lateinit var user_pass: EditText
    lateinit var privacy_policy: CheckBox
    lateinit var btnlogin: Button
    lateinit var tvlogin: TextView
    lateinit var register: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        user_name = findViewById(R.id.et_name)
        user_email = findViewById(R.id.et_email)
        user_pass = findViewById(R.id.et_pass)
        privacy_policy = findViewById(R.id.cb_politcy_privacy)
        btnlogin = findViewById(R.id.btn_login)
        tvlogin = findViewById(R.id.tv_login)
        register = findViewById(R.id.btn_register)

        register.setOnClickListener{
            //obtener texto
            val _user = user_name.text.toString()
            val _email = user_email.text.toString()
            val _pass = user_pass.text.toString()
            val user= Usuario(_user,"","",_email,"",3,_pass,this,"insert",_user)

            if (_user.isEmpty() || _pass.isEmpty() || _pass.isEmpty()){
                Toast.makeText(this, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            }else{
                user.executeService("http://localhost/MyArt/Usuario.php")
                Toast.makeText(this, "Register succesfully.", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)

            }
        }

        btnlogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        tvlogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}