package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.MisTiemposDAO
import com.julen.swimcrono.model.entity.MisTiempos

class MisTiemposService(private val misTiemposDao: MisTiemposDAO) {

    // Obtener todos los registros
    suspend fun getAllMisTiempos(): List<MisTiempos> {
        return misTiemposDao.getAll()
    }

    // Obtener un registro por su ID
    suspend fun getMisTiemposById(id: Long): List<MisTiempos> {
        return misTiemposDao.getById(id)
    }

    // Insertar uno o varios registros
    suspend fun insertarMisTiempos(vararg misTiempos: MisTiempos) {
        misTiemposDao.insertAll(*misTiempos)
    }

    // Borrar un registro
    suspend fun borrarMisTiempos(misTiempos: MisTiempos) {
        misTiemposDao.delete(misTiempos)
    }

    // Obtener todos los tiempos de una prueba específica
    suspend fun getMisTiemposDePrueba(idPrueba: Long): List<MisTiempos> {
        return misTiemposDao.getMisTiemposDePrueba(idPrueba)
    }
}
