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

class LoginActivity : AppCompatActivity() {

    lateinit var register: TextView
    lateinit var login: Button
    lateinit var user: EditText
    lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login = findViewById(R.id.btn_login)
        register = findViewById(R.id.tv_return_register)
        user = findViewById(R.id.et_user)
        pass = findViewById(R.id.et_pass)

        login.setOnClickListener{
            //obtener texto
            val _user = user.text.toString()
            val _pass = pass.text.toString()

            if (_user.isEmpty() || _pass.isEmpty()){
                Toast.makeText(this@LoginActivity, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@LoginActivity, "Login succesfully.", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }
    }
}