package com.example.myart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class GalleryProfileActivity : AppCompatActivity() {

    lateinit var btnGallery: Button
    lateinit var btnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_profile)

        btnGallery = findViewById(R.id.btn_gallery_profile)
        btnSend = findViewById(R.id.btn_send)

        btnSend.setOnClickListener{
            Log.e("del", "subir imagen")
        }

        btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            val image = findViewById<ImageView>(R.id.iv_image_profile)
            image.setImageURI(imageUri)
        }

    }
}