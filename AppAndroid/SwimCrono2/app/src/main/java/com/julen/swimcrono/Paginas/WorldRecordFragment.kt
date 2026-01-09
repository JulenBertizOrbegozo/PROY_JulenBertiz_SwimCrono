package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.service.PruebaService
import com.julen.swimcrono.ui.adapters.WorldRecordAdapter
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView

class WorldRecordFragment : Fragment(R.layout.fragment_world_record) {

    private lateinit var cardMasc: CardView
    private lateinit var cardFem: CardView
    private lateinit var cardPiscinaCorta: CardView
    private lateinit var cardPiscinaLarga: CardView

    private var mascElegido = true
    private var piscinaCortaElegido = false
    private lateinit var adapter: WorldRecordAdapter
    private lateinit var pruebaService: PruebaService


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = LocalDatabase.getInstance(requireContext())
        pruebaService = PruebaService(database.pruebaDAO())

        adapter = WorldRecordAdapter()

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerWorldRecords)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        cardMasc = view.findViewById(R.id.cardMasc)
        cardFem = view.findViewById(R.id.cardFem)
        cardPiscinaCorta = view.findViewById(R.id.cardPiscinaCorta)
        cardPiscinaLarga = view.findViewById(R.id.cardPiscinaLarga)

        initComponents(view)

        cargarWorldRecords()
    }


    private fun initComponents(view: View) {
        val ctx = requireContext()

        cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
        cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))

        cardMasc.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            mascElegido = true
            cargarWorldRecords()

        }

        cardFem.setOnClickListener {
            cardMasc.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            cardFem.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            mascElegido = false
            cargarWorldRecords()

        }

        cardPiscinaCorta.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            piscinaCortaElegido = true
            cargarWorldRecords()

        }

        cardPiscinaLarga.setOnClickListener {
            cardPiscinaCorta.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_background))
            cardPiscinaLarga.setCardBackgroundColor(ContextCompat.getColor(ctx, R.color.app_card_selected))
            piscinaCortaElegido = false
            cargarWorldRecords()

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

    private fun cargarWorldRecords() {
        val genero = if (mascElegido) "Masc" else "Fem"
        val piscina = if (piscinaCortaElegido) "25m" else "50m"

        viewLifecycleOwner.lifecycleScope.launch {
            val records = pruebaService.getWorldRecordsFiltrados(genero, piscina)
            adapter.actualizarLista(records)
        }
    }

}
