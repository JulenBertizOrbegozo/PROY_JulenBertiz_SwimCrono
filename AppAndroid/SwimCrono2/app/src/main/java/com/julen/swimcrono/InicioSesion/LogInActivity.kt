package com.julen.swimcrono.InicioSesion


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import com.julen.swimcrono.Paginas.MainActivity
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.service.UsuarioService

class LogInActivity : AppCompatActivity() {
    private lateinit var txtemail: TextView
    private lateinit var txtcontraseina : TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initEmail(view: View) {
        txtemail = findViewById<TextView>(R.id.txtEmail)
        txtcontraseina = findViewById<TextInputLayout>(R.id.tilPassword)

    }

    fun restablecerContraseña(view: View) {}
    fun CrearCuenta(view: View) {
        var intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    suspend fun login(view: View) {
        val email = txtemail.text.toString()
        val contraseina = txtcontraseina.editText?.text.toString()
        val usuarioDao = LocalDatabase.getInstance(this).usuarioDAO()
        val usuarioService = UsuarioService(usuarioDao)
        //TODO Hashear la contraseña
        val usuario = usuarioService.getUsuarioByMailAndPass(email, contraseina)
        if (usuario != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            usuario.activo = true
            //Actualizar usuario a activo true
        }else{
            //TODO sacar error usuario inexistente
        }


    }
}