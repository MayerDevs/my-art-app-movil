package com.example.myart

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.IOException

class CameraActivity : AppCompatActivity() {


    lateinit var con_con: ImageView
    lateinit var _tip_con: Button
    lateinit var txt_con: EditText

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var selectedImageUri: Uri
    private lateinit var imageView: ImageView
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        imageView = findViewById(R.id.iv_content)
        val btnGallery = findViewById<ImageView>(R.id.btn_gallery)
        val btnCamera = findViewById<ImageView>(R.id.btn_camera)
        val btnUpload = findViewById<Button>(R.id.btn_upload)
        con_con = findViewById(R.id.iv_content)

        val selectButton = findViewById<ImageView>(R.id.btn_gallery)
        selectButton.setOnClickListener {
            openImagePicker()
        }

        val uploadButton = findViewById<Button>(R.id.btn_upload)
        uploadButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun uploadImage() {
        val imageRef = storageRef.child("images/${selectedImageUri.lastPathSegment}")
        val uploadTask = imageRef.putFile(selectedImageUri)

        uploadTask.addOnSuccessListener {
            // La imagen se ha cargado exitosamente
            // Puedes obtener la URL de descarga de la imagen subida usando: it.metadata?.reference?.downloadUrl
        }.addOnFailureListener {
            Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data!!
            displaySelectedImage()
        }
    }

    private fun displaySelectedImage() {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(contentResolver, selectedImageUri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
        }

        imageView.setImageBitmap(bitmap)
    }
}

