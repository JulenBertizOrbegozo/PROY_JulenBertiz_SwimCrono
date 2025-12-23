package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.PruebaDAO
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.relations.PruebaConMisTiempos
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

class PruebaService(private val pruebaDao : PruebaDAO) {
    suspend fun insertarPrueba(prueba: Prueba) {
        pruebaDao.insertarPrueba(prueba)
    }
    suspend fun borrarPrueba(prueba:Prueba){
        pruebaDao.delete(prueba)
    }
    suspend fun getPruebas(): List<Prueba>{
        return pruebaDao.getAll()
    }
    suspend fun getPruebaConMisTiempos(idPrueba: Long): PruebaConMisTiempos {
        return pruebaDao.getPruebaConMisTiempos(idPrueba)
    }

    suspend fun getPruebaConWorldRecord(idPrueba: Long): PruebaConWorldRecord {
        return pruebaDao.getPruebaConWorldRecord(idPrueba)
    }
}