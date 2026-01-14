package com.julen.swimcrono.InicioSesion


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.julen.swimcrono.Paginas.MainActivity
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.service.UsuarioService
import kotlinx.coroutines.launch
import kotlin.math.log

class LogInActivity : AppCompatActivity() {
    private lateinit var login : Button
    private lateinit var txtemail: TextView
    private lateinit var txtError: TextView
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
        lifecycleScope.launch {
            inicioAutomatico()
        }

        txtemail = findViewById<TextView>(R.id.txtEmail)
        txtcontraseina = findViewById<TextInputLayout>(R.id.tilPassword)
        txtError = findViewById(R.id.txtError)
        login = findViewById(R.id.btnLogin)
        login.setOnClickListener {
            lifecycleScope.launch {
                iniciarSesion()
            }
        }
    }

    fun restablecerContraseña(view: View) {}
    fun CrearCuenta(view: View) {
        var intent = Intent(this, CreateAccountActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    suspend fun iniciarSesion() {
        val email = txtemail.text.toString()
        var contraseina = txtcontraseina.editText?.text.toString()
        val usuarioDao = LocalDatabase.getInstance(this).usuarioDAO()
        val usuarioService = UsuarioService(usuarioDao)
        contraseina = usuarioService.hashPassword(contraseina)
        val usuario = usuarioService.getUsuarioByMailAndPass(email, contraseina)
        if (usuario != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            usuario.activo = true
            usuarioService.updateUser(usuario)
            txtError.visibility = GONE
        }else{
            txtError.text = "No existe ningún usuario con ese correo y contraseña"
            txtError.visibility = VISIBLE
            return
        }


    }
    suspend fun inicioAutomatico(){
        val usuarioDao = LocalDatabase.getInstance(this).usuarioDAO()
        val usuarioService = UsuarioService(usuarioDao)
        val usuarioActivo = usuarioService.getUserActivo()
        if (usuarioActivo != null){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}