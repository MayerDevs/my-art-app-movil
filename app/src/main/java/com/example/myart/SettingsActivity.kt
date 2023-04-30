package com.example.myart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import java.lang.annotation.RetentionPolicy

class SettingsActivity : AppCompatActivity() {
    lateinit var previous: ImageView
    lateinit var notification: Button
    lateinit var policy_privacy: Button
    lateinit var follow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        previous = findViewById(R.id.iv_previous)
        notification = findViewById(R.id.btn_notification)
        policy_privacy = findViewById(R.id.btn_policy_privacy)
        follow = findViewById(R.id.btn_follow)

        previous.setOnClickListener{
            Toast.makeText(this, "previous.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
        notification.setOnClickListener{
            Toast.makeText(this, "notification.", Toast.LENGTH_SHORT).show()
        }
        policy_privacy.setOnClickListener{
            Toast.makeText(this, "policy and privacy.", Toast.LENGTH_SHORT).show()
        }
        follow.setOnClickListener{
            Toast.makeText(this, "follows.", Toast.LENGTH_SHORT).show()
            val i = Intent(this, FollowActivity::class.java)
            startActivity(i)
        }
    }
}