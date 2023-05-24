package com.example.myart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import java.io.IOException

class CameraActivity : AppCompatActivity() {


    lateinit var con_con: ImageView
    lateinit var _tip_con: Button
    lateinit var _txt_con: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val btnGallery = findViewById<ImageView>(R.id.btn_gallery)
        val btnCamera = findViewById<ImageView>(R.id.btn_camera)
        con_con = findViewById(R.id.iv_content)

        btnCamera.setOnClickListener {
            Toast.makeText(this, "camera open", Toast.LENGTH_LONG).show()
            startForResultRight.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        //btnUpload.setOnClickListener { }

        btnGallery.setOnClickListener {
            showFileChooser()
        }


    }
    private val PICK_IMAGE_REQUEST = 1
    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selecciona imagen"), PICK_IMAGE_REQUEST)
    }

    private val startForResultRight =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                var imageBitmap = intent?.extras?.get("data") as Bitmap
                con_con.setImageBitmap(imageBitmap)
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath: Uri = data.data!!
            try {
                var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                con_con.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}