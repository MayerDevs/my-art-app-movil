package com.example.myart

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myart.data.Content
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class CameraActivity : AppCompatActivity() {


    lateinit var con_con: ImageView
    lateinit var _tip_con: Button
    lateinit var txt_con: EditText
    private val auth= FirebaseAuth.getInstance()

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var selectedImageUri: Uri
    private lateinit var imageView: ImageView
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    val contenidoList = mutableListOf<Content>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        imageView = findViewById(R.id.iv_content)
        val btnCamera = findViewById<ImageView>(R.id.btn_camera)
        con_con = findViewById(R.id.iv_content)
        txt_con = findViewById(R.id.et_text)



        btnCamera.setOnClickListener {
            Toast.makeText(this, "Camera open", Toast.LENGTH_LONG).show()
            startForResultRight.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        val selectButton = findViewById<ImageView>(R.id.btn_gallery)
        selectButton.setOnClickListener {
            openImagePicker()
        }

        val uploadButton = findViewById<Button>(R.id.btn_upload)

        uploadButton.setOnClickListener {
            val drawable = imageView.drawable
            val bitmap = (drawable as BitmapDrawable).bitmap
            uploadImage(bitmap)


            finish()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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

        // Aquí debes reemplazar "coleccion" con el nombre de tu colección en Firestore
        val documentRef = firestore.collection("Contenido").document()

        var post = Content(imageUrl, txt_con.text.toString())
        post.uid=auth.currentUser!!.uid

        firestore.collection("Contenido").add(post)
            .addOnSuccessListener {
                // Los datos se han guardado exitosamente en Firestore
            }
            .addOnFailureListener { e ->
                // Error al guardar los datos en Firestore
                Toast.makeText(this, "Error al guardar los datos en Firestore", Toast.LENGTH_LONG).show()
                Log.e(TAG, "Error al guardar los datos en Firestore", e)
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

    private val startForResultRight = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)

            uploadImage(imageBitmap) // Subir la imagen a Firebase Storage
        }
    }
}

