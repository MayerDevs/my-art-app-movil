package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.myart.clases.Usuario

class RegisterNextActivity : AppCompatActivity() {
    lateinit var _cor_usu: EditText
    lateinit var _eda_usu: EditText
    lateinit var _con_usu: EditText
    lateinit var _con_usu_verified: EditText
    lateinit var policy_privacy: CheckBox
    lateinit var btn_register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_next)

        _cor_usu = findViewById(R.id.cor_usu)
        _eda_usu = findViewById(R.id.eda_usu)
        _con_usu = findViewById(R.id.con_usu)
        val bl = getIntent().getExtras();
        var nom_usu = bl?.getString("nom_usu")
        var ape_usu = bl?.getString("ape_usu")
        var tip_usu = bl?.getString("tip_usu")
        var cel_usu:Int = bl?.getInt("tel_usu")!!
        var cor_usuU = bl?.getString("cor_usuU")
        var eda_usuU = bl?.getString("eda_usuU")
        var con_usuU = bl?.getString("con_usuU")
        if(cor_usuU!=null){
            _cor_usu.setText(cor_usuU)
            _eda_usu.setText(eda_usuU)
            _con_usu.setText(con_usuU)
        }

        _con_usu_verified = findViewById(R.id.con_usu_verified)
        policy_privacy = findViewById(R.id.cb_politcy_privacy)
        btn_register = findViewById(R.id.btn_register)

        btn_register.setOnClickListener {
            //obtener texto
            val cor_usu = _cor_usu.text.toString()
            val eda_usu = _eda_usu.text.toString()
            val con_usu = _con_usu.text.toString()

            //val con_usu_verified = _con_usu_verified.text.toString()

            var user= Usuario(nom_usu.toString(),ape_usu.toString(),tip_usu.toString(),cor_usu,eda_usu,cel_usu,con_usu,this,"insert",nom_usu.toString())
            if(cor_usuU!=null){
                user= Usuario(nom_usu.toString(),ape_usu.toString(),tip_usu.toString(),cor_usu,eda_usu,cel_usu,con_usu,this,"update",nom_usu.toString())
            }

            if (cor_usu.isEmpty() || eda_usu.isEmpty() || con_usu.isEmpty() || con_usu.isEmpty() || policy_privacy.isSelected) {
                Toast.makeText(this, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            } else {
                user.Register("http://192.168.80.18/MyArt/Usuario.php")
              //  Toast.makeText(this, "Register succesfully.", Toast.LENGTH_SHORT).show()
               val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }
    }
}