package com.julen.swimcrono.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.julen.swimcrono.model.entity.MisTiempos

@Dao
interface MisTiemposDAO {
    @Query("select * from mis_tiempos")
    fun getAll(): List<MisTiempos>

    @Query("select * from mis_tiempos where id = :misTiemposId")
    fun getById(misTiemposId : Long) : List<MisTiempos>

    @Insert
    fun insertAll(vararg misTiempos: MisTiempos)

    @Delete
    fun delete(misTiempos:MisTiempos)

    @Query("select * from mis_tiempos where prueba_id = :idPrueba")
    suspend fun getMisTiemposDePrueba(idPrueba : Long): List<MisTiempos>
}