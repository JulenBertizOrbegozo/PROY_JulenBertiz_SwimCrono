package com.julen.swimcrono.model.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.WorldRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseCallback(
    private val context: Context
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            val database = LocalDatabase.getInstance(context)

            val pruebaDao = database.pruebaDAO()
            val worldRecordDao = database.worldRecordDAO()

            val prueba100Libre = Prueba(
                distancia = 100,
                estilo = "Libre",
                genero = "Masculino",
                piscina = "50m"
            )

            val pruebaId = pruebaDao.insertarPrueba(prueba100Libre)

            val record = WorldRecord(
                pruebaId = pruebaId,
                tiempo = "00:46.91",
                nombre = "César",
                apellido = "Cielo",
                fecha = "2009-07-30"
            )

            worldRecordDao.insert(record)
        }
    }
}
