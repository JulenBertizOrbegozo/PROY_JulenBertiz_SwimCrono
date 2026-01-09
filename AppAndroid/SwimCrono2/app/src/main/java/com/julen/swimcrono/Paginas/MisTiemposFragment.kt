package com.julen.swimcrono.Paginas

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.service.MisTiemposService
import com.julen.swimcrono.ui.adapters.MisTiemposAdapter
import kotlinx.coroutines.launch


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
    private lateinit var misTiemposService: MisTiemposService
    private lateinit var adapter: MisTiemposAdapter
    private lateinit var txtError: TextView
    private lateinit var layoutPruebas : LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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

        val database = LocalDatabase.getInstance(requireContext())
        misTiemposService = MisTiemposService(database.misTiemposDAO())

        adapter = MisTiemposAdapter()
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerMisTiempos)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        txtError = view.findViewById<TextView>(R.id.txtError)
        layoutPruebas = view.findViewById<LinearLayout>(R.id.layoutPruebas)
        cargarMisTiempos()

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
            cargarMisTiempos()
        }
        cardEstiloCrol.setOnClickListener {
            selectCardEstilos(cardEstiloCrol)
            cardEstiloElegido = cardEstiloCrol
            cargarMisTiempos()
        }
        cardEstiloMariposa.setOnClickListener {
            selectCardEstilos(cardEstiloMariposa)
            cardEstiloElegido = cardEstiloMariposa
            cargarMisTiempos()
        }
        cardEstiloEspalda.setOnClickListener {
            selectCardEstilos(cardEstiloEspalda)
            cardEstiloElegido = cardEstiloEspalda
            cargarMisTiempos()
        }
        cardEstiloBraza.setOnClickListener {
            selectCardEstilos(cardEstiloBraza)
            cardEstiloElegido = cardEstiloBraza
            cargarMisTiempos()
        }
        cardEstiloEstilos.setOnClickListener {
            selectCardEstilos(cardEstiloEstilos)
            cardEstiloElegido = cardEstiloEstilos
            cargarMisTiempos()
        }

        card50m.setOnClickListener {
            selectCardDistancia(card50m)
            cardDistanciaElegido = card50m
            cargarMisTiempos()
        }
        card100m.setOnClickListener {
            selectCardDistancia(card100m)
            cardDistanciaElegido = card100m
            cargarMisTiempos()
        }
        card200m.setOnClickListener {
            selectCardDistancia(card200m)
            cardDistanciaElegido = card200m
            cargarMisTiempos()
        }
        card400m.setOnClickListener {
            selectCardDistancia(card400m)
            cardDistanciaElegido = card400m
            cargarMisTiempos()
        }
        card800m.setOnClickListener {
            selectCardDistancia(card800m)
            cardDistanciaElegido = card800m
            cargarMisTiempos()
        }
        card1500m.setOnClickListener {
            selectCardDistancia(card1500m)
            cardDistanciaElegido = card1500m
            cargarMisTiempos()
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

    private fun cargarMisTiempos(){
        val distancia = cardDistanciaElegido.findViewById<TextView>(R.id.textOption).text.toString()
        val partesDistancia = distancia.split("m")
        val distanciaInt = partesDistancia[0].toInt()
        var estilo = cardEstiloElegido.findViewById<TextView>(R.id.textOption).text.toString()
        if (estilo.uppercase() == "CROL") estilo = "Libre"
        viewLifecycleOwner.lifecycleScope.launch {
            if (estilo.uppercase() == "TODOS"){
                val misTiempos = misTiemposService.getMisTiemposConPruebaSoloDistancia(distanciaInt)
                Log.d("MIS_TIEMPOS", "Resultados: ${misTiempos.size}")
                Log.d("MIS_TIEMPOS", "Distancia: ${distanciaInt}")
                if (misTiempos.isEmpty()){
                    layoutPruebas.visibility = View.GONE
                    txtError.text = "No hay registros de esta distancia"
                    txtError.visibility = View.VISIBLE
                }else{
                    layoutPruebas.visibility = View.VISIBLE
                    txtError.visibility = View.GONE
                }
                adapter.actualizarLista(misTiempos)
            }else{
                val misTiempos = misTiemposService.getMisTiemposConPrueba(distanciaInt, estilo)
                if (misTiempos.isEmpty()){
                    layoutPruebas.visibility = View.GONE
                    txtError.text = "No hay registros de esta prueba"
                    txtError.visibility = View.VISIBLE
                }else{
                    layoutPruebas.visibility = View.VISIBLE
                    txtError.visibility = View.GONE
                }
                adapter.actualizarLista(misTiempos)
            }
        }
    }
}