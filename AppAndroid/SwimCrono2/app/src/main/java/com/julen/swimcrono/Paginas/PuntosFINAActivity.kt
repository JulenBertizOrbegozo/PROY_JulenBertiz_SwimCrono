package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.julen.swimcrono.R

class PuntosFINAActivity : AppCompatActivity() {
    private lateinit var opcionesEstilos: Spinner
    private lateinit var opcionesDistancia: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_puntos_finaactivity)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
    }

    private fun initComponents() {
        opcionesEstilos = findViewById(R.id.opcionEstilos)
        opcionesDistancia = findViewById(R.id.opcionDistancia)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.opcionesEstilos,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        opcionesEstilos.adapter = adapter
        val adapter2 = ArrayAdapter.createFromResource(
            this,
            R.array.opcionesDistancia,
            android.R.layout.simple_spinner_item
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        opcionesDistancia.adapter = adapter2

    }
}