package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.MisTiemposDAO
import com.julen.swimcrono.model.entity.MisTiempos
import com.julen.swimcrono.model.relations.TiempoConPrueba

class MisTiemposService(private val misTiemposDao: MisTiemposDAO) {

    suspend fun getAllMisTiempos(): List<MisTiempos> {
        return misTiemposDao.getAll()
    }

    suspend fun getMisTiemposById(id: Long): List<MisTiempos> {
        return misTiemposDao.getById(id)
    }

    suspend fun insert(vararg misTiempos: MisTiempos) {
        misTiemposDao.insertAll(*misTiempos)
    }

    suspend fun borrarMisTiempos(misTiempos: MisTiempos) {
        misTiemposDao.delete(misTiempos)
    }

    suspend fun getMisTiemposDePrueba(idPrueba: Long): List<MisTiempos> {
        return misTiemposDao.getMisTiemposDePrueba(idPrueba)
    }
    suspend fun getMisTiemposConPrueba(distancia: Int, estilo: String): List<TiempoConPrueba> {
        val pruebasConTiempos = misTiemposDao.getPruebasConMisTiempos(distancia, estilo)
        val resultado = mutableListOf<TiempoConPrueba>()
        for (p in pruebasConTiempos) {
            for (t in p.misTiempos) {
                resultado.add(TiempoConPrueba(t, p.prueba))
            }
        }
        return resultado
    }

    suspend fun getMisTiemposConPruebaSoloDistancia(distancia: Int): List<TiempoConPrueba> {
        val pruebasConTiempos = misTiemposDao.getPruebasConMisTiemposSoloDistancia(distancia)
        val resultado = mutableListOf<TiempoConPrueba>()
        for (p in pruebasConTiempos) {
            for (t in p.misTiempos) {
                resultado.add(TiempoConPrueba(t, p.prueba))
            }
        }
        return resultado
    }


}
