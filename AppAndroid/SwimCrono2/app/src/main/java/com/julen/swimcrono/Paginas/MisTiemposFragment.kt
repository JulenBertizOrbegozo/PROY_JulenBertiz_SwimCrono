package com.julen.swimcrono.Paginas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.julen.swimcrono.R



class MisTiemposFragment : Fragment() {
    private lateinit var cardDistanciaElegido : CardView
    private lateinit var cardEstiloElegido : CardView
    private lateinit var card50m : CardView
    private lateinit var card100m : CardView
    private lateinit var card200m : CardView
    private lateinit var card400m : CardView
    private lateinit var card800m : CardView
    private lateinit var card1500m : CardView
    private lateinit var cardEstiloTodos : CardView
    private lateinit var cardEstiloMariposa : CardView
    private lateinit var cardEstiloEspalda : CardView
    private lateinit var cardEstiloBraza : CardView
    private lateinit var cardEstiloCrol : CardView
    private lateinit var cardEstiloEstilos : CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mis_tiempos, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card50m = view.findViewById<CardView>(R.id.distancia50m)
        card100m = view.findViewById<CardView>(R.id.distancia100m)
        card200m = view.findViewById<CardView>(R.id.distancia200m)
        card400m = view.findViewById<CardView>(R.id.distancia400m)
        card800m = view.findViewById<CardView>(R.id.distancia800m)
        card1500m = view.findViewById<CardView>(R.id.distancia1500m)

        cardEstiloTodos = view.findViewById<CardView>(R.id.estiloTodos)
        cardEstiloCrol = view.findViewById<CardView>(R.id.estiloCrol)
        cardEstiloMariposa = view.findViewById<CardView>(R.id.estiloMariposa)
        cardEstiloEspalda = view.findViewById<CardView>(R.id.estiloEspalda)
        cardEstiloBraza = view.findViewById<CardView>(R.id.estiloBraza)
        cardEstiloEstilos = view.findViewById<CardView>(R.id.estiloEstilos)
        initCards()
    }

    private fun initCards() {
        setCardText(cardEstiloTodos, "Todos")
        selectCardEstilos(cardEstiloTodos)
        cardEstiloElegido = cardEstiloTodos
        setCardText(cardEstiloCrol, "Crol")
        setCardText(cardEstiloMariposa, "Mariposa")
        setCardText(cardEstiloEspalda, "Espalda")
        setCardText(cardEstiloBraza, "Braza")
        setCardText(cardEstiloEstilos, "Estilos")

        setCardText(card50m, "50m")
        selectCardDistancia(card50m)
        cardDistanciaElegido = card50m
        setCardText(card100m, "100m")
        setCardText(card200m, "200m")
        setCardText(card400m, "400m")
        setCardText(card800m, "800m")
        setCardText(card1500m, "1500m")

        cardEstiloTodos.setOnClickListener {
            selectCardEstilos(cardEstiloTodos)
            cardEstiloElegido = cardEstiloTodos
        }
        cardEstiloCrol.setOnClickListener {
            selectCardEstilos(cardEstiloCrol)
            cardEstiloElegido = cardEstiloCrol
        }
        cardEstiloMariposa.setOnClickListener {
            selectCardEstilos(cardEstiloMariposa)
            cardEstiloElegido = cardEstiloMariposa
        }
        cardEstiloEspalda.setOnClickListener {
            selectCardEstilos(cardEstiloEspalda)
            cardEstiloElegido = cardEstiloEspalda
        }
        cardEstiloBraza.setOnClickListener {
            selectCardEstilos(cardEstiloBraza)
            cardEstiloElegido = cardEstiloBraza
        }
        cardEstiloEstilos.setOnClickListener {
            selectCardEstilos(cardEstiloEstilos)
            cardEstiloElegido = cardEstiloEstilos
        }

        card50m.setOnClickListener {
            selectCardDistancia(card50m)
            cardDistanciaElegido = card50m
        }
        card100m.setOnClickListener {
            selectCardDistancia(card100m)
            cardDistanciaElegido = card100m
        }
        card200m.setOnClickListener {
            selectCardDistancia(card200m)
            cardDistanciaElegido = card200m
        }
        card400m.setOnClickListener {
            selectCardDistancia(card400m)
            cardDistanciaElegido = card400m
        }
        card800m.setOnClickListener {
            selectCardDistancia(card800m)
            cardDistanciaElegido = card800m
        }
        card1500m.setOnClickListener {
            selectCardDistancia(card1500m)
            cardDistanciaElegido = card1500m
        }
    }


    private fun setCardText(card: CardView, text: String) {
        val textView = card.findViewById<TextView>(R.id.textOption)
        textView.text = text
    }
    private fun unselectCardEstilos(){
        cardEstiloBraza.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        cardEstiloMariposa.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        cardEstiloEspalda.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        cardEstiloEstilos.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        cardEstiloCrol.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        cardEstiloTodos.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
    }
    private fun selectCardEstilos(selected: CardView) {
        unselectCardEstilos()
        selected.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_selected))
    }
    private fun unselectCardDistancia(){
        card50m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        card100m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        card200m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        card400m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        card800m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
        card1500m.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_background))
    }
    private fun selectCardDistancia(selected: CardView) {
        unselectCardDistancia()
        selected.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.app_card_selected))
    }
}