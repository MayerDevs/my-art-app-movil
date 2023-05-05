package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.myart.clases.Usuario

class RegisterActivity : AppCompatActivity() {

    lateinit var usu_usu: EditText
    lateinit var cor_usu: EditText
    lateinit var con_usu: EditText
    lateinit var privacy_policy: CheckBox
    lateinit var btnlogin: Button
    lateinit var tvlogin: TextView
    lateinit var register: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usu_usu = findViewById(R.id.et_name)
        cor_usu = findViewById(R.id.et_email)
        con_usu = findViewById(R.id.et_pass)
        privacy_policy = findViewById(R.id.cb_politcy_privacy)
        btnlogin = findViewById(R.id.btn_login)
        tvlogin = findViewById(R.id.tv_login)
        register = findViewById(R.id.btn_register)

        register.setOnClickListener{
            //obtener texto
            val usu_usu = usu_usu.text.toString()
            val cor_usu = cor_usu.text.toString()
            val con_usu = con_usu.text.toString()
            val user= Usuario(usu_usu,"","",cor_usu,"",3,con_usu,this,"insert",usu_usu)

            if (usu_usu.isEmpty() || con_usu.isEmpty() || con_usu.isEmpty()){
                Toast.makeText(this, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            }else{
                user.executeService("http://192.168.80.18/MyArt/Usuario.php")
                //Toast.makeText(this, "Register succesfully.", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                //startActivity(i)

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