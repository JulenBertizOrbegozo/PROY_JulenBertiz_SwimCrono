package com.julen.swimcrono.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.julen.swimcrono.R
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

class WorldRecordAdapter(
    private var lista: List<PruebaConWorldRecord> = emptyList()
) : RecyclerView.Adapter<WorldRecordAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPrueba: TextView = view.findViewById(R.id.txtPrueba)
        val txtTiempo: TextView = view.findViewById(R.id.txtTiempo)
        val txtNadador: TextView = view.findViewById(R.id.txtNadador)
        val txtFecha: TextView = view.findViewById(R.id.txtFecha)
        val txtAnio : TextView = view.findViewById(R.id.txtAnio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reusable_item_world_record, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        if (item.worldRecord == null) {
            println("⚠️ WorldRecord NULL en ${item.prueba.distancia} ${item.prueba.estilo}")
            return
        }

        val wr = item.worldRecord
        holder.txtPrueba.text = "${item.prueba.distancia}m ${item.prueba.estilo}"
        holder.txtTiempo.text = formatearTiempo(wr.tiempo)
        holder.txtNadador.text = "${wr.nombre} ${wr.apellido}"
        holder.txtFecha.text = obtenerMesDia(wr.fecha)
        holder.txtAnio.text = obtenerAnio(wr.fecha)
    }




    fun actualizarLista(nuevaLista: List<PruebaConWorldRecord>) {
        lista = nuevaLista
        notifyDataSetChanged()
    }

    fun formatearTiempo(tiempo: String): String {
        val partes = tiempo.split(":")
        if (partes.size == 2) {
            val minutos = partes[0].trimStart('0')
            val segundos = partes[1]
            if (partes[0].equals("00")) return segundos

            val minutoFormateado = if (minutos.isEmpty()) "0" else minutos
            return "$minutoFormateado:$segundos"
        }
        return tiempo
    }
    fun obtenerAnio(fecha: String): String {
        val partes = fecha.split("-")
        return partes[0]
    }
    fun obtenerMesDia(fecha: String): String{
        var salida = ""
        val partes = fecha.split("-")
        val mes =  when (partes[1]) {
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



        return "${dia} ${mes}"
    }
}
