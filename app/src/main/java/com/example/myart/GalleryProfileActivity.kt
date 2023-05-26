package com.example.myart

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myart.data.Content
import com.example.myart.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class GalleryProfileActivity : AppCompatActivity() {

    lateinit var btnGallery: Button
    lateinit var btnSend: Button
    var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageView: ImageView
    private val storage = Firebase.storage
    private val storageRef = storage.reference

    private val auth = FirebaseAuth.getInstance()
    private  val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_profile)

        btnGallery = findViewById(R.id.btn_gallery_profile)
        btnSend = findViewById(R.id.btn_send)
        imageView = findViewById(R.id.iv_image_profile)

        btnSend.setOnClickListener{
            val drawable = imageView.drawable
            val bitmap = (drawable as BitmapDrawable).bitmap
            uploadImage(bitmap)
        }

        btnGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    private fun uploadImage(imageBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageRef.child("images/${System.currentTimeMillis()}.jpg")
        val uploadTask = imageRef.putBytes(data)

        uploadTask.addOnSuccessListener { taskSnapshot ->
            // La imagen se ha cargado exitosamente
            val imageRef = taskSnapshot.metadata?.reference
            imageRef?.downloadUrl?.addOnSuccessListener { downloadUri ->
                // URL de descarga de la imagen
                val imageUrl = downloadUri.toString()

                // Guardar la URL en Firebase Firestore
                saveImageUrlToFirestore(imageUrl)
            }
        }.addOnFailureListener {
            // Error al subir la imagen
            Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_LONG).show()
        }

    }

    private fun saveImageUrlToFirestore(imageUrl: String) {
        val firestore = FirebaseFirestore.getInstance()


        val documentRef = firestore.collection("Usuarios").document()

        val post = User(auth.currentUser!!.uid, imageUrl)

        firestore.collection("Usuarios").add(post)
            .addOnSuccessListener {
                // Los datos se han guardado exitosamente en Firestore
            }
            .addOnFailureListener { e ->
                // Error al guardar los datos en Firestore
                Toast.makeText(this, "Error al guardar los datos en Firestore", Toast.LENGTH_LONG).show()
                Log.e(ContentValues.TAG, "Error al guardar los datos en Firestore", e)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            selectedImageUri  = data?.data
            val image = findViewById<ImageView>(R.id.iv_image_profile)
            image.setImageURI(selectedImageUri)
        }
    }
}