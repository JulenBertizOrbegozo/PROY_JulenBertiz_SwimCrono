package com.julen.swimcrono.Paginas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.julen.swimcrono.R
import com.julen.swimcrono.model.database.LocalDatabase
import com.julen.swimcrono.model.relations.PruebaConWorldRecord
import com.julen.swimcrono.model.service.PruebaService
import com.julen.swimcrono.model.service.WorldRecordService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FinaFragment : Fragment() {

    private lateinit var opcionesEstilos: Spinner
    private lateinit var opcionesDistancia: Spinner

    private lateinit var cardMasc: CardView
    private lateinit var cardFem: CardView
    private lateinit var cardPiscinaCorta: CardView
    private lateinit var cardPiscinaLarga: CardView
    private lateinit var cardPuntos : CardView
    private lateinit var txtPuntos : TextView
    private lateinit var txtError : TextView

    private var mascElegido = true
    private var piscinaCortaElegido = true
    private var estiloSeleccionado: String = ""
    private var distanciaSeleccionada: String = ""


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
        initPuntos(view)
    }

    private fun initPuntos(view: View) {
        cardPuntos = view.findViewById<CardView>(R.id.cardPuntos)
        txtPuntos = view.findViewById<TextView>(R.id.txtPuntos)
        txtError = view.findViewById<TextView>(R.id.txtError)
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
        opcionesEstilos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                estiloSeleccionado = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                estiloSeleccionado = ""
            }
        }
        opcionesDistancia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                distanciaSeleccionada = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                distanciaSeleccionada = ""
            }
        }
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
            calcularPuntos(view)
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
    private fun calcularPuntos(view: View) {

        val genero = if (mascElegido) "Masc" else "Fem"
        val piscina = if (piscinaCortaElegido) "25m" else "50m"

        val inputDisplay = view.findViewById<EditText>(R.id.inputDisplay)
        val valor = inputDisplay.text.toString()

        if (valor.isBlank()) {
            cardPuntos.visibility = View.GONE
            txtError.text = "Todos los datos no introducidos"
            txtError.visibility = View.VISIBLE
            return
        }

        val database = LocalDatabase.getInstance(requireContext())
        val pruebaService = PruebaService(database.pruebaDAO())
        val worldRecordService = WorldRecordService(database.worldRecordDAO())

        viewLifecycleOwner.lifecycleScope.launch {

            val puntosFina = withContext(Dispatchers.IO) {

                val idPrueba = pruebaService.getPruebaID(
                    distanciaSeleccionada.toInt(),
                    estiloSeleccionado,
                    genero,
                    piscina
                ) ?: return@withContext 0

                val pruebaConWR = pruebaService.getPruebaConWorldRecord(idPrueba)

                val worldRecord = pruebaConWR.worldRecord
                    ?: return@withContext 0

                val tiempoWR = convertirASegundos(worldRecord.tiempo)
                val tiempoUsuario = convertirASegundos(valor)

                if (tiempoWR <= 0 || tiempoUsuario <= 0) return@withContext 0

                calcularPuntos(tiempoUsuario, tiempoWR)
            }


            // 5️⃣ Actualizar UI (MAIN THREAD)
            if (puntosFina == 0 || puntosFina < 0) {
                txtError.text = "No se ha encontrado la prueba"
                txtError.visibility = View.VISIBLE
                cardPuntos.visibility = View.GONE
            }else{
                txtError.visibility = View.GONE
                txtPuntos.text = puntosFina.toString()
                cardPuntos.alpha = 0f
                cardPuntos.visibility = View.VISIBLE
                cardPuntos.animate().alpha(1f).setDuration(300).start()
            }
        }
    }




    fun convertirASegundos(tiempo: String): Double {
        val partes = tiempo.split(":", ".")

        val minutos = partes[0].toInt()
        val segundos = partes[1].toInt()
        val centesimas = partes[2].toInt()

        return minutos * 60 + segundos + centesimas / 100.0
    }
    fun calcularPuntos(tiempoAtleta: Double, tiempoWR: Double): Int {
        val puntos = 1000 * Math.pow(tiempoWR / tiempoAtleta, 3.0)
        return puntos.toInt()
    }

}


