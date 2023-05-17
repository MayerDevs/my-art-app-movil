package com.example.myart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val btnGallery = findViewById<ImageView>(R.id.btn_gallery)
        val btnCamera = findViewById<ImageView>(R.id.btn_camera)


        btnCamera.setOnClickListener{
            Toast.makeText(this, "camera open", Toast.LENGTH_LONG).show()
            startForResultRight.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
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
            val image = findViewById<ImageView>(R.id.iv_content)
            image.setImageURI(imageUri)
        }

    }

    private val startForResultRight = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: androidx.activity.result.ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val image = findViewById<ImageView>(R.id.iv_content)
            image.setImageBitmap(imageBitmap)
        }
    }
}