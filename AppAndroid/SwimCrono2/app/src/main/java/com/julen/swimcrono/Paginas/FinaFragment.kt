package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.julen.swimcrono.R

class FinaFragment : Fragment() {

    private lateinit var opcionesEstilos: Spinner
    private lateinit var opcionesDistancia: Spinner

    private lateinit var cardMasc: CardView
    private lateinit var cardFem: CardView
    private lateinit var cardPiscinaCorta: CardView
    private lateinit var cardPiscinaLarga: CardView

    private var mascElegido = true
    private var piscinaCortaElegido = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fina, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // REFERENCIAS
        opcionesEstilos = view.findViewById(R.id.opcionEstilos)
        opcionesDistancia = view.findViewById(R.id.opcionDistancia)

        cardMasc = view.findViewById(R.id.cardMasc)
        cardFem = view.findViewById(R.id.cardFem)
        cardPiscinaCorta = view.findViewById(R.id.cardPiscinaCorta)
        cardPiscinaLarga = view.findViewById(R.id.cardPiscinaLarga)

        initSpinners()
        initCards()
        initButton(view)
    }

    private fun initSpinners() {
        val adapterEstilos = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.opcionesEstilos,
            android.R.layout.simple_spinner_item
        )
        adapterEstilos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        opcionesEstilos.adapter = adapterEstilos

        val adapterDistancia = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.opcionesDistancia,
            android.R.layout.simple_spinner_item
        )
        adapterDistancia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        opcionesDistancia.adapter = adapterDistancia
    }

    private fun initCards() {
        setCardText(cardMasc, "Masculino")
        setCardText(cardFem, "Femenino")
        setCardText(cardPiscinaCorta, "25m")
        setCardText(cardPiscinaLarga, "50m")

        cardMasc.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_selected))
        cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_selected))

        cardMasc.setOnClickListener {
            selectCard(cardMasc, cardFem)
            mascElegido = true
        }

        cardFem.setOnClickListener {
            selectCard(cardFem, cardMasc)
            mascElegido = false
        }

        cardPiscinaCorta.setOnClickListener {
            selectCard(cardPiscinaCorta, cardPiscinaLarga)
            piscinaCortaElegido = true
        }

        cardPiscinaLarga.setOnClickListener {
            selectCard(cardPiscinaLarga, cardPiscinaCorta)
            piscinaCortaElegido = false
        }
    }

    private fun initButton(view: View) {
        val button = view.findViewById<Button>(R.id.reusableButton)
        button.text = "Calcular"
        button.setOnClickListener {
            calcularPuntos()
        }
    }

    private fun setCardText(card: CardView, text: String) {
        val textView = card.findViewById<TextView>(R.id.textOption)
        textView.text = text
    }

    private fun selectCard(selected: CardView, unselected: CardView) {
        selected.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_selected))
        unselected.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
    }
    private fun calcularPuntos() {
        val genero :String
        val piscina : String
        if (mascElegido)
            genero = "masc"
        else
            genero = "fem"
        if (piscinaCortaElegido)
            piscina = "25m"
        else
            piscina= "50m"

    }
}


