package com.julen.swimcrono.model.service

import android.widget.Toast
import com.julen.swimcrono.model.dao.PruebaDAO
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.relations.PruebaConMisTiempos
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

class PruebaService(private val pruebaDao : PruebaDAO) {

    suspend fun getPruebas(): List<Prueba> {
        return pruebaDao.getAll()
    }

    suspend fun getPruebaConMisTiempos(idPrueba: Long): PruebaConMisTiempos {
        return pruebaDao.getPruebaConMisTiempos(idPrueba)
    }

    suspend fun getPruebaConWorldRecord(idPrueba: Long): PruebaConWorldRecord {
        return pruebaDao.getPruebaConWorldRecord(idPrueba)
    }

    suspend fun getPruebaID(distancia: Int, estilo: String, genero: String, piscina: String): Long? {
        // 🔹 Normalizar datos aquí
        val estiloBD = when (estilo.lowercase()) {
            "crol", "libre" -> "Libre"
            "mariposa" -> "Mariposa"
            "braza" -> "Braza"
            "espalda" -> "Espalda"
            "estilos" -> "Estilos"
            else -> estilo
        }

        val generoBD = when (genero.lowercase()) {
            "masculino", "hombre", "hombres" -> "Masc"
            "femenino", "mujer", "mujeres" -> "Fem"
            else -> genero
        }

        val piscinaBD = when (piscina.lowercase()) {
            "piscina corta", "25m", "25 metros", "25" -> "25m"
            "piscina larga", "50m", "50 metros", "50" -> "50m"
            else -> piscina
        }

        return pruebaDao.getIdFromEstiloDistancia(estiloBD, distancia, generoBD, piscinaBD)
    }
    suspend fun getWorldRecordsFiltrados(genero: String, piscina: String): List<PruebaConWorldRecord>{
        return pruebaDao.getWorldRecordsFiltrados(genero, piscina)
    }
}
