package com.julen.swimcrono.InicioSesion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.julen.swimcrono.Paginas.PuntosFINAActivity
import com.julen.swimcrono.R

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun btnCrearCuenta(view: View) {
        var intent = Intent(this, PuntosFINAActivity::class.java)
        startActivity(intent)
    }
    fun IniciarSesion(view: View) {
        var intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}