package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.WorldRecordDAO
import com.julen.swimcrono.model.entity.WorldRecord
import com.julen.swimcrono.model.relations.PruebaConWorldRecord

class WorldRecordService(private val worldRecordDao: WorldRecordDAO) {
    suspend fun insertarWorldRecord(worldRecord: WorldRecord) {
        worldRecordDao.insert(worldRecord)
    }
    suspend fun borrarWorldRecord(worldRecord: WorldRecord){
        worldRecordDao.delete(worldRecord)
    }
    suspend fun getAllWorldRecord(): List<WorldRecord>{
        return worldRecordDao.getAll()
    }
    fun getWorldRecord(id: Long?): WorldRecord{
        return worldRecordDao.getById(id)
    }

}