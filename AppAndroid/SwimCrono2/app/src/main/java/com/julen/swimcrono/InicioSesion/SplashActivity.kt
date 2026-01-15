package com.julen.swimcrono.InicioSesion

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.julen.swimcrono.Paginas.MainActivity
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.service.UsuarioService
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            comprobarSesion()
        }
    }

    private suspend fun comprobarSesion() {
        // Splash de 2 segundos
        kotlinx.coroutines.delay(2000)

        val usuarioDao = LocalDatabase.getInstance(this).usuarioDAO()
        val usuarioService = UsuarioService(usuarioDao)
        val usuarioActivo = usuarioService.getUserActivo()

        val intent = if (usuarioActivo != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LogInActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}
