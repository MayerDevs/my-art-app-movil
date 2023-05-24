package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.myart.clases.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterNextActivity : AppCompatActivity() {
    lateinit var _cor_usu: EditText
    lateinit var _eda_usu: EditText
    lateinit var _con_usu: EditText
    lateinit var _con_usu_verified: EditText
    lateinit var policy_privacy: CheckBox
    lateinit var btn_register: Button
    private val auth= FirebaseAuth.getInstance()
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

            //val con_usu_verified = _con_usu_verified.text.toString(

            if (cor_usu.isEmpty() || eda_usu.isEmpty() || con_usu.isEmpty() || con_usu.isEmpty() || policy_privacy.isSelected) {
                Toast.makeText(this, "Pleace, fill all details.", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(cor_usu,con_usu)
                    .addOnSuccessListener {
                        var profile=UserProfileChangeRequest.Builder()
                            .setDisplayName(nom_usu).build()

                        it.user!!.updateProfile(profile)
                            .addOnSuccessListener {
                                finish()
                                val user=auth.currentUser
                                val uid=user!!.uid
                                val map = hashMapOf(
                                    "nom_usu" to nom_usu,
                                    "ape_usu" to ape_usu,
                                    "tip_usu" to tip_usu,
                                    "cel_usu" to cel_usu,
                                    "eda_usu" to eda_usu

                                )
                                val db= Firebase.firestore

                                db.collection("Usuarios").document(uid).set(map)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "User register", Toast.LENGTH_SHORT).show()

                                    }
                                    .addOnFailureListener{
                                        Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()

                                    }


                            }
                            .addOnFailureListener{
                                Toast.makeText(this, "User or password wrong", Toast.LENGTH_SHORT).show()

                            }

                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "User or password wrong", Toast.LENGTH_SHORT).show()
                    }

              //  Toast.makeText(this, "Register succesfully.", Toast.LENGTH_SHORT).show()
               val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }
    }
}