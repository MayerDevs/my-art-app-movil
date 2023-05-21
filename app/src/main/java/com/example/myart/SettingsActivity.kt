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

class SettingsActivity : AppCompatActivity() {
    lateinit var previous: ImageView
    lateinit var notification: Button
    lateinit var policy_privacy: Button
    lateinit var follow: Button
    lateinit var close: Button
    lateinit var delete: Button
    lateinit var update: Button
    var DbHelper= DbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        delete = findViewById(R.id.btn_delete_account)
        previous = findViewById(R.id.iv_previous)
        notification = findViewById(R.id.btn_notification)
        policy_privacy = findViewById(R.id.btn_policy_privacy)
        follow = findViewById(R.id.btn_follow)
        close = findViewById(R.id.btn_close_session)
        update = findViewById(R.id.btn_update)

        previous.setOnClickListener{
            Toast.makeText(this, "previous.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
        notification.setOnClickListener{
            Toast.makeText(this, "notification.", Toast.LENGTH_SHORT).show()
        }
        close.setOnClickListener{
            var DbHelper: DbHelper
            DbHelper= DbHelper(this)
            var del=DbHelper.delete(1)
            if(del>0){
                Toast.makeText(this, "Close session succesfully", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)

            }
            else{
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
            }

        }
        policy_privacy.setOnClickListener{
            Toast.makeText(this, "policy and privacy.", Toast.LENGTH_SHORT).show()
        }
        update.setOnClickListener{
            val db: SQLiteDatabase =DbHelper.readableDatabase
            val cursor=db.rawQuery("SELECT * FROM Usuarios",null)
           // Toast.makeText(this, "Start session", Toast.LENGTH_SHORT).show()
            if(cursor.moveToFirst()){
                var cor_usu=cursor.getString(1)
                var URL="http://192.168.80.18/MyArt/Usuario.php?cor_usu=$cor_usu&consulta=consult"
                val jsonRequest= JsonObjectRequest(
                    Request.Method.GET,URL,null,
                    { response ->
                        val i = Intent(this, RegisterActivity::class.java)
                        i.putExtra("nom_usu",response.getString("nom_usu"))
                        i.putExtra("ape_usu",response.getString("ape_usu"))
                        i.putExtra("cor_usu",response.getString("cor_usu"))
                        i.putExtra("tip_usu",response.getString("tip_usu"))
                        i.putExtra("cel_usu",response.getString("cel_usu"))
                        i.putExtra("eda_usu",response.getString("eda_usu"))
                        i.putExtra("con_usu",response.getString("con_usu"))
                        i.putExtra("Update",true)
                        Toast.makeText(this,response.getString("cel_usu") , Toast.LENGTH_SHORT).show()
                        startActivity(i)

                    },
                    {
                        Toast.makeText(this, "Something went wrong with the consult.", Toast.LENGTH_SHORT).show()

                    })
                val requestQueue = Volley.newRequestQueue(this)
                requestQueue.add(jsonRequest)

            }
            else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            //   var user= Usuario("","","", cor_usu,"",0,"",this,"consult","")

        }

        delete.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            i.putExtra("log",true)
            startActivity(i)

        }
        follow.setOnClickListener{
            Toast.makeText(this, "follows.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, FollowActivity::class.java)
            startActivity(i)
        }
    }
}