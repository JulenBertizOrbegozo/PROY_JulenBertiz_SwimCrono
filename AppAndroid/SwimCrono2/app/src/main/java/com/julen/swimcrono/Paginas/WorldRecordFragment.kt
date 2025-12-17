package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.julen.swimcrono.R

class WorldRecordFragment : Fragment(R.layout.fragment_world_record) {

    private lateinit var cardMasc: CardView
    private lateinit var cardFem: CardView
    private lateinit var cardPiscinaCorta: CardView
    private lateinit var cardPiscinaLarga: CardView

    private var mascElegido = true
    private var piscinaCortaElegido = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias
        cardMasc = view.findViewById(R.id.cardMasc)
        cardFem = view.findViewById(R.id.cardFem)
        cardPiscinaCorta = view.findViewById(R.id.cardPiscinaCorta)
        cardPiscinaLarga = view.findViewById(R.id.cardPiscinaLarga)

        initComponents(view)
    }

    private fun initComponents(view: View) {
        val ctx = requireContext()

        cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
        cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))

        cardMasc.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            mascElegido = true
        }

        cardFem.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            mascElegido = false
        }

        cardPiscinaCorta.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            piscinaCortaElegido = true
        }

        cardPiscinaLarga.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            piscinaCortaElegido = false
        }

        setCardText(view, R.id.cardMasc, "Masculino")
        setCardText(view, R.id.cardFem, "Femenino")
        setCardText(view, R.id.cardPiscinaCorta, "25m")
        setCardText(view, R.id.cardPiscinaLarga, "50m")
    }

    private fun setCardText(view: View, cardId: Int, text: String) {
        val card = view.findViewById<CardView>(cardId)
        val textView = card.findViewById<TextView>(R.id.textOption)
        textView.text = text
    }
}
