package com.example.myart

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayOutputStream
import java.util.*
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
class CameraActivity : AppCompatActivity() {

    lateinit var _con_con: ImageView
    lateinit var _txt_con: EditText
    lateinit var btnUpload: Button
    lateinit var btnGallery: ImageView
    lateinit var btnCamera: ImageView
    lateinit var optionsContent: Spinner
    lateinit var contentImageMain: ImageView

    lateinit var imageBitmap: Bitmap

    var UPLOAD_URL: String = "https://192.168.101.3/MyArt/Contenido.php"

    var KEY_IMAGE = "con_con"
    var KEY_NOMBRE = "txt_con"

    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        btnGallery = findViewById(R.id.btn_gallery)
        btnCamera = findViewById(R.id.btn_camera)
        optionsContent = findViewById(R.id.options_content)
        _txt_con = findViewById(R.id.et_text)
        _con_con = findViewById(R.id.iv_content)
        btnUpload = findViewById(R.id.btn_upload)
        contentImageMain = findViewById(R.id.iv_content)

        val adapter = ArrayAdapter.createFromResource(   this, R.array.type_content,  android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        optionsContent.setAdapter(adapter);

        btnUpload.setOnClickListener {
            /*val content_type = optionsContent.selectedItem.toString()
            val txt_con = _txt_con.text.toString()

            val multimedia = Multimedia(
                ide_usu,
                content_type,
                txt_con,
                ide_lik,
                con_con
            )

            multimedia.Add("http://192.168.101.3/MyArt/Usuario.php")
            //  Toast.makeText(this, "Register succesfully.", Toast.LENGTH_SHORT).show()
            // val i = Intent(this, MainActivity::class.java)
            //startActivity(i)*/
            uploadImage()

        }

        btnCamera.setOnClickListener {
            Toast.makeText(this, "camera open", Toast.LENGTH_LONG).show()
            startForResultRight.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        btnGallery.setOnClickListener {
            /*val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)*/
             */
            showFileChooser()
        }
    }

    fun getStringImage(bmp: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageBytes = baos.toByteArray()
        val encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT)

        return encodedImage
    }

    fun uploadImage() {
        val loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor")
        val stringRequest = object : StringRequest(Request.Method.POST, UPLOAD_URL,
            Response.Listener<String> { response ->
                loading.dismiss()
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { error ->
                loading.dismiss()
                Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String>? {
                val imagen = bitmap?.let { getStringImage(it) } as String
                val nombre = _txt_con.text.toString().trim()

                val params: MutableMap<String, String> = Hashtable()
                params[KEY_IMAGE] = imagen
                params[KEY_NOMBRE] = nombre

                return params
            }
        }

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    private val PICK_IMAGE_REQUEST = 1
    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selecciona imagen"), PICK_IMAGE_REQUEST)
    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            imageView = findViewById(R.id.iv_content)
            imageView.setImageURI(imageUri)
        }

    }
*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val filePath: Uri = data.data!!
            try {
                // Obtener el mapa de bits de la galería
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                // Configurar el mapa de bits en ImageView
                _con_con.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    private val startForResultRight =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: androidx.activity.result.ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                imageBitmap = intent?.extras?.get("data") as Bitmap
                _con_con = findViewById(R.id.iv_content)
                _con_con.setImageBitmap(imageBitmap)
            }
        }
}