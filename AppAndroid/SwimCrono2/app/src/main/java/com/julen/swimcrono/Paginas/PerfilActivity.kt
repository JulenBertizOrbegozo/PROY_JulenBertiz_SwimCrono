package com.julen.swimcrono.Paginas

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.julen.swimcrono.R
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.util.Base64

class PerfilActivity : AppCompatActivity() {

    private lateinit var imageProfile: ImageView
    private lateinit var buttonTakePhoto: Button

    private lateinit var buttonGaleria: Button

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_PERMISSION = 100
        private val REQUEST_GALLERY = 2  // código de solicitud diferente
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        imageProfile = findViewById(R.id.imagenPerfil)
        buttonTakePhoto = findViewById(R.id.botonSacarFoto)
        buttonGaleria = findViewById(R.id.botonElegirGaleria)

        buttonTakePhoto.setOnClickListener {
            if (checkPermissions()) {
                openCamera()
            } else {
                requestPermissions()
            }
        }

        buttonGaleria.setOnClickListener {
            openGallery()
        }
    }

    private fun checkPermissions(): Boolean {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return cameraPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            REQUEST_PERMISSION
        )
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } else {
            Toast.makeText(this, "No se pudo abrir la cámara (emulador o dispositivo sin cámara)", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera()
            } else {
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Abrir galería
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*" // solo imágenes
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun base64ToBitmap(encodedString: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK || data == null) return

        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                val imageBitmap = data.extras?.get("data") as? Bitmap
                imageBitmap?.let {
                    imageProfile.setImageBitmap(it)
                    saveImageToDatabase(it)
                }
            }
            REQUEST_GALLERY -> {
                val imageUri = data.data
                imageUri?.let {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                    imageProfile.setImageBitmap(bitmap)
                    saveImageToDatabase(bitmap)
                }
            }
        }
    }

    private fun saveImageToDatabase(bitmap: Bitmap) {
        val base64Image = bitmapToBase64(bitmap)
        // Aquí llamas a tu DAO o repositorio
        // Ejemplo:
        // val usuario = getUsuarioActual()
        // usuario.imagen = base64Image
        // usuarioDao.updateUsuario(usuario)
    }

}
