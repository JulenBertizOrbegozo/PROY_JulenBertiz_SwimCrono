package com.julen.swimcrono.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.julen.swimcrono.R
import com.julen.swimcrono.model.relations.TiempoConPrueba
import java.time.Year
import java.util.Calendar

class MisTiemposAdapter(
    private var lista: List<TiempoConPrueba> = emptyList()
) : RecyclerView.Adapter<MisTiemposAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPrueba: TextView = view.findViewById(R.id.txtPrueba)
        val txtTiempo: TextView = view.findViewById(R.id.txtTiempo)
        val txtFecha: TextView = view.findViewById(R.id.txtFecha)
        val image: ImageView = view.findViewById(R.id.imagen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reusable_item_mis_tiempos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.txtPrueba.text = "${item.prueba.distancia}m ${item.prueba.estilo}"
        holder.txtTiempo.text = formatearTiempo(item.tiempo.tiempo)
        holder.txtFecha.text = obtenerFecha(item.tiempo.fecha)
    }

    fun actualizarLista(nuevaLista: List<TiempoConPrueba>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    fun formatearTiempo(tiempo: String): String {
        val partes = tiempo.split(":")
        if (partes.size == 2) {
            val minutos = partes[0].trimStart('0')
            val segundos = partes[1]
            val minutoFormateado = if (minutos.isEmpty()) "0" else minutos
            return if (minutoFormateado == "0") segundos else "$minutoFormateado:$segundos"
        }
        return tiempo
    }

    fun obtenerFecha(fecha: String): String {
        val partes = fecha.split("-")
        val mes = when (partes[1]) {
            "01" -> "ENE"
            "02" -> "FEB"
            "03" -> "MAR"
            "04" -> "ABR"
            "05" -> "MAY"
            "06" -> "JUN"
            "07" -> "JUL"
            "08" -> "AGO"
            "09" -> "SEP"
            "10" -> "OCT"
            "11" -> "NOV"
            "12" -> "DIC"
            else -> "MES"
        }
        val dia = partes[2]
        val anio = partes[0].toInt()

        val anioActual = Calendar.getInstance().get(Calendar.YEAR)

        if (anio.equals(anioActual)){
            return "$dia $mes"

        }else{
            return "$dia $mes $anio"

        }
    }
}

