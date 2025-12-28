package com.julen.swimcrono.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.julen.swimcrono.model.entity.WorldRecord
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

@Dao
interface WorldRecordDAO {
    @Query("select * from world_record")
    fun getAll(): List<WorldRecord>

    @Query("select * from world_record where prueba_id =:misWorldRecordId limit 1")
    fun getById(misWorldRecordId: Long?) : WorldRecord

    @Insert
    fun insert(vararg misWorldRecord: WorldRecord)

    @Delete
    fun delete(misWorldRecord:WorldRecord)

}