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
    suspend fun getWorldRecord(): List<WorldRecord>{
        return worldRecordDao.getAll()
    }
    suspend fun getWorldRecordConPrueba(idWorldRecord: Long) : PruebaConWorldRecord{
        return worldRecordDao.getWorldRecordDePrueba(idWorldRecord)
    }
}