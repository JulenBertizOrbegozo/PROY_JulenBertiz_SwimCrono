package com.julen.swimcrono.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.julen.swimcrono.model.dao.MisTiemposDAO
import com.julen.swimcrono.model.dao.PruebaDAO
import com.julen.swimcrono.model.dao.UsuarioDAO
import com.julen.swimcrono.model.dao.WorldRecordDAO
import com.julen.swimcrono.model.entity.MisTiempos
import com.julen.swimcrono.model.entity.Usuario
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.WorldRecord

@Database(entities = [Usuario::class, Prueba::class, MisTiempos::class, WorldRecord::class], version = 1)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun usuarioDAO() : UsuarioDAO
    abstract fun pruebaDAO() : PruebaDAO
    abstract fun misTiemposDAO() : MisTiemposDAO
    abstract fun worldRecordDAO(): WorldRecordDAO

    companion object{
        private const val DATABASE_NAME = "SwimCrono.db"

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context : Context) : LocalDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, DATABASE_NAME)
                        .addCallback(DatabaseCallback(context))
                        .build()
                    INSTANCE  = instance
                }
                return instance
            }
        }
    }
}