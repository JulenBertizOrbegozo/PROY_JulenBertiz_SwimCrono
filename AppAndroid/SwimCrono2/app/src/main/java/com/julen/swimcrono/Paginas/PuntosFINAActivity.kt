package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.julen.swimcrono.R

class PuntosFINAActivity : AppCompatActivity() {
    private lateinit var opcionesEstilos: Spinner
    private lateinit var opcionesDistancia: Spinner

    private val cardMasc: CardView by lazy { findViewById(R.id.cardMasc) }
    private val cardFem: CardView by lazy { findViewById(R.id.cardFem) }
    private val cardPiscinaCorta: CardView by lazy { findViewById(R.id.cardPiscinaCorta) }
    private val cardPiscinaLarga: CardView by lazy { findViewById(R.id.cardPiscinaLarga) }
    private var mascElegido: Boolean = true;
    private var piscinaCortaElegido: Boolean = true;


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
        setCardText(R.id.cardMasc, "Masculino")

        cardMasc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
        cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
        cardMasc.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_background))
            mascElegido = true
        }
        cardFem.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_background))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
            mascElegido = false
        }
        cardPiscinaCorta.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_background))
            piscinaCortaElegido = true
        }
        cardPiscinaLarga.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_background))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(this, R.color.app_card_selected))
            piscinaCortaElegido = false
        }


        setCardText(R.id.cardFem, "Femenino")
        setCardText(R.id.cardPiscinaCorta, "25m")
        setCardText(R.id.cardPiscinaLarga, "50m")
        setButtonText(R.id.reusableButton, "Calcular")





    }

    private fun setCardText(cardId: Int, text: String) {
        val card = findViewById<CardView>(cardId)
        val textView = card.findViewById<TextView>(R.id.textOption)
        textView.text = text
    }

    private fun setButtonText(buttonId: Int, text: String) {
        val button = findViewById<Button>(buttonId)
        button.text = text
    }

}