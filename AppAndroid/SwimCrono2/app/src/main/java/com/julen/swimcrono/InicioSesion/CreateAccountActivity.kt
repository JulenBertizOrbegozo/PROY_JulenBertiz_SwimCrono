package com.julen.swimcrono.InicioSesion

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.julen.swimcrono.Paginas.MainActivity
import com.julen.swimcrono.R


class CreateAccountActivity : AppCompatActivity() {
    var tilEmail: TextInputLayout? = null
    var tilPassword: TextInputLayout? = null
    var tilNombre: TextInputLayout? = null
    var tilConfirmPassword: TextInputLayout? = null
    var etEmail: TextInputEditText? = null
    var etPassword: TextInputEditText? = null
    var etNombre: TextInputEditText? = null
    var etConfirmPassword: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vincular()
    }
    fun vincular(){
        tilNombre = findViewById(R.id.tilNombre);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etPassword!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!s.toString().isEmpty()) {
                    tilPassword!!.setError(null)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etConfirmPassword!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tilConfirmPassword!!.setError(null)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    fun btnCrearCuenta(view: View) {

        val nombre = etNombre!!.getText().toString().trim { it <= ' ' }
        val email = etEmail!!.getText().toString().trim { it <= ' ' }
        val password = etPassword!!.getText().toString().trim { it <= ' ' }
        val confirmPassword = etConfirmPassword!!.getText().toString().trim { it <= ' ' }


        if (nombre.isEmpty()) {
            tilNombre!!.setError("El nombre es obligatorio")
            return
        } else {
            tilNombre!!.setError(null)
        }

        if (email.isEmpty()) {
            tilEmail!!.setError("El correo es obligatorio")
            return
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail!!.setError("Correo no válido")
            return
        } else {
            tilEmail!!.setError(null)
        }

        val pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/]).{8,20}$"

        if (password.isEmpty()) {
            tilPassword!!.setError("La contraseña es obligatoria")
            return
        } else if (!password.matches(pattern.toRegex())) {
            tilPassword!!.setError("Debe tener 8-20 caracteres, una mayúscula, un número y un símbolo")
            return
        } else {
            tilPassword!!.setError(null) // Todo correcto
        }

        if (password != confirmPassword) {
            tilConfirmPassword!!.setError("Las contraseñas no coinciden")
            return
        } else {
            tilConfirmPassword!!.setError(null)
        }

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun IniciarSesion(view: View) {
        volverAtras(view)
    }

    fun volverAtras(view: View) {
        val intent: Intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

    }
}