package com.julen.swimcrono.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.WorldRecord
import com.julen.swimcrono.model.relations.PruebaConMisTiempos
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

@Dao
interface PruebaDAO {
    @Query("select * from prueba")
    fun getAll(): List<Prueba>

    @Query("select * from prueba where id = :pruebaId")
    fun getById(pruebaId : Long) : List<Prueba>

    @Insert
    suspend fun insertarPrueba(prueba: Prueba): Long

    @Delete
    fun delete(prueba:Prueba)

    @Transaction
    @Query("select * from prueba where id = :idPrueba")
    suspend fun getPruebaConMisTiempos(idPrueba: Long) : PruebaConMisTiempos

    @Transaction
    @Query("select * from prueba where id = :idPrueba")
    suspend fun getPruebaConWorldRecord(idPrueba : Long) : PruebaConWorldRecord
}