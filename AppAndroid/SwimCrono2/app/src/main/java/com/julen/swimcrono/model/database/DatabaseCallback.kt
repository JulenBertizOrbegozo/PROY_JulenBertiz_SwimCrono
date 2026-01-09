package com.julen.swimcrono.model.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.julen.swimcrono.model.dao.MisTiemposDAO
import com.julen.swimcrono.model.dao.PruebaDAO
import com.julen.swimcrono.model.dao.WorldRecordDAO
import com.julen.swimcrono.model.entity.MisTiempos
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.WorldRecord
import com.julen.swimcrono.model.service.MisTiemposService
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
            val misTiemposDao  = database.misTiemposDAO()
            val misTiemposService = MisTiemposService(misTiemposDao)

            ainadirDatosPruebasYWR(pruebaDao, worldRecordDao)
            insertarMisTiempos(misTiemposService)
        }
    }

    private suspend fun ainadirDatosPruebasYWR(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        inicializarCrol(pruebaDAO, recordDAO)
        inicializarMariposa(pruebaDAO, recordDAO)
        inicializarEspalda(pruebaDAO, recordDAO)
        inicializarBraza(pruebaDAO, recordDAO)
        inicializarEstilos(pruebaDAO, recordDAO)
    }
    private suspend fun inicializarCrol(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        crol50(pruebaDAO, recordDAO)
        crol100(pruebaDAO, recordDAO)
        crol200(pruebaDAO, recordDAO)
        crol400(pruebaDAO, recordDAO)
        crol800(pruebaDAO, recordDAO)
        crol1500(pruebaDAO, recordDAO)
    }
    private suspend fun inicializarBraza(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        braza50(pruebaDAO, recordDAO)
        braza100(pruebaDAO, recordDAO)
        braza200(pruebaDAO, recordDAO)
    }
    private suspend fun inicializarMariposa(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        mariposa50(pruebaDAO, recordDAO)
        mariposa100(pruebaDAO, recordDAO)
        mariposa200(pruebaDAO, recordDAO)
    }
    private suspend fun inicializarEspalda(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        espalda50(pruebaDAO, recordDAO)
        espalda100(pruebaDAO, recordDAO)
        espalda200(pruebaDAO, recordDAO)
    }
    private suspend fun inicializarEstilos(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO){
        estilos100(pruebaDAO, recordDAO)
        estilos200(pruebaDAO, recordDAO)
        estilos400(pruebaDAO, recordDAO)
    }

    //Crol
    private suspend fun crol50(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:20.16",
                nombre = "Caeleb",
                apellido = "Dressel",
                fecha = "2018-12-13"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:22.93",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2017-10-07"
            )
        )

        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:20.91",
                nombre = "Cesar",
                apellido = "Cielo",
                fecha = "2009-08-02"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:23.61",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2017-07-23"
            )
        )
    }
    private suspend fun crol100(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:46.91",
                nombre = "Cesar",
                apellido = "Cielo",
                fecha = "2009-07-30"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:44.84",
                nombre = "Kyle",
                apellido = "Chalmers",
                fecha = "2021-10-29"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:51.71",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2017-07-23"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:50.25",
                nombre = "Cate",
                apellido = "Campbell",
                fecha = "2017-12-03"
            )
        )
    }
    private suspend fun crol200(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "01:42.00",
                nombre = "Paul",
                apellido = "Biedermann",
                fecha = "2009-07-28"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "01:39.37",
                nombre = "Paul",
                apellido = "Biedermann",
                fecha = "2009-11-15"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "01:52.85",
                nombre = "Mollie",
                apellido = "OCallaghan",
                fecha = "2023-07-26"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "01:50.43",
                nombre = "Siobhan",
                apellido = "Haughey",
                fecha = "2021-10-30"
            )
        )
    }
    private suspend fun crol400(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "03:40.07",
                nombre = "Paul",
                apellido = "Biedermann",
                fecha = "2009-07-26"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "03:32.25",
                nombre = "Dan",
                apellido = "Horton",
                fecha = "2021-12-10"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "03:56.46",
                nombre = "Katie",
                apellido = "Ledecky",
                fecha = "2016-08-08"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "03:53.92",
                nombre = "Sarah",
                apellido = "Sjöström",
                fecha = "2020-11-15"
            )
        )
    }
    private suspend fun crol800(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 800,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "07:32.12",
                nombre = "Zhou",
                apellido = "Ying",
                fecha = "2023-07-27"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 800,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "07:23.42",
                nombre = "Peter",
                apellido = "John",
                fecha = "2022-11-15"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 800,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "08:04.79",
                nombre = "Katie",
                apellido = "Ledecky",
                fecha = "2016-08-12"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 800,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "07:59.34",
                nombre = "Katherine",
                apellido = "Siegemund",
                fecha = "2020-12-18"
            )
        )
    }
    private suspend fun crol1500(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 1500,
                estilo = "Libre",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "14:31.02",
                nombre = "Greg",
                apellido = "Rogers",
                fecha = "2001-07-29"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 1500,
                estilo = "Libre",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "14:08.06",
                nombre = "Velimir",
                apellido = "Stjepanovic",
                fecha = "2018-12-12"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 1500,
                estilo = "Libre",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "15:20.48",
                nombre = "Katie",
                apellido = "Ledecky",
                fecha = "2018-08-12"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 1500,
                estilo = "Libre",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "15:08.40",
                nombre = "Katie",
                apellido = "Ledecky",
                fecha = "2021-12-17"
            )
        )
    }

    //Mariposa
    private suspend fun mariposa50(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:22.27",
                nombre = "Caeleb",
                apellido = "Dressel",
                fecha = "2021-07-31"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:21.75",
                nombre = "Chad",
                apellido = "Le Clos",
                fecha = "2020-11-21"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:24.43",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2017-07-23"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:23.61",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2017-07-23"
            )
        )
    }
    private suspend fun mariposa100(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:49.50",
                nombre = "Caeleb",
                apellido = "Dressel",
                fecha = "2021-07-31"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:47.78",
                nombre = "Chad",
                apellido = "Le Clos",
                fecha = "2020-11-21"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:55.48",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2016-08-07"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:54.05",
                nombre = "Maggie",
                apellido = "MacNeil",
                fecha = "2022-12-16"
            )
        )
    }
    private suspend fun mariposa200(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "01:50.73",
                nombre = "Kristof",
                apellido = "Milak",
                fecha = "2023-07-24"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Mariposa",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "01:48.24",
                nombre = "Chad",
                apellido = "Le Clos",
                fecha = "2018-12-12"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "02:03.37",
                nombre = "Zhang",
                apellido = "Yufei",
                fecha = "2023-07-25"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Mariposa",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "02:00.45",
                nombre = "Siobhan",
                apellido = "Haughey",
                fecha = "2021-10-30"
            )
        )
    }

    //Braza
    private suspend fun braza50(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Braza",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:25.95",
                nombre = "Adam",
                apellido = "Peaty",
                fecha = "2017-07-21"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Braza",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:24.95",
                nombre = "Ilya",
                apellido = "Shymanovich",
                fecha = "2020-12-19"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Braza",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:29.30",
                nombre = "Lilly",
                apellido = "King",
                fecha = "2017-07-21"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Braza",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:28.56",
                nombre = "Alia",
                apellido = "Atkinson",
                fecha = "2018-12-12"
            )
        )
    }
    private suspend fun braza100(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Braza",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:56.88",
                nombre = "Adam",
                apellido = "Peaty",
                fecha = "2019-08-21"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Braza",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:55.28",
                nombre = "Ilya",
                apellido = "Shymanovich",
                fecha = "2021-12-18"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Braza",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "01:04.13",
                nombre = "Lilly",
                apellido = "King",
                fecha = "2017-08-07"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Braza",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "01:02.36",
                nombre = "Alia",
                apellido = "Atkinson",
                fecha = "2018-12-12"
            )
        )
    }
    private suspend fun braza200(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Braza",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "02:06.12",
                nombre = "Anton",
                apellido = "Chupkov",
                fecha = "2019-07-28"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Braza",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "02:00.16",
                nombre = "Ilya",
                apellido = "Shymanovich",
                fecha = "2021-12-18"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Braza",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "02:18.51",
                nombre = "Lilly",
                apellido = "King",
                fecha = "2017-08-07"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Braza",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "02:14.57",
                nombre = "Alia",
                apellido = "Atkinson",
                fecha = "2018-12-12"
            )
        )
    }

    //Espalda
    private suspend fun espalda50(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:23.55",
                nombre = "Kliment",
                apellido = "Kolesnikov",
                fecha = "2023-07-27"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:22.22",
                nombre = "Kliment",
                apellido = "Kolesnikov",
                fecha = "2021-11-02"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:26.86",
                nombre = "Kaylee",
                apellido = "McKeown",
                fecha = "2023-07-25"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 50,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:25.60",
                nombre = "Maggie",
                apellido = "MacNeil",
                fecha = "2022-12-17"
            )
        )
    }
    private suspend fun espalda100(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "00:51.85",
                nombre = "Ryan",
                apellido = "Murphy",
                fecha = "2016-08-12"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:49.90",
                nombre = "Kliment",
                apellido = "Kolesnikov",
                fecha = "2020-12-17"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "00:57.45",
                nombre = "Kaylee",
                apellido = "McKeown",
                fecha = "2023-07-25"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:55.03",
                nombre = "Maggie",
                apellido = "MacNeil",
                fecha = "2022-12-16"
            )
        )
    }
    private suspend fun espalda200(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "01:51.92",
                nombre = "Aaron",
                apellido = "Peirsol",
                fecha = "2009-07-31"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Espalda",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "01:46.00",
                nombre = "Ryan",
                apellido = "Murphy",
                fecha = "2020-12-12"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "02:03.35",
                nombre = "Regan",
                apellido = "Smith",
                fecha = "2019-07-28"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Espalda",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "01:59.23",
                nombre = "Maddie",
                apellido = "Groves",
                fecha = "2021-12-17"
            )
        )
    }

    //Estilos
    private suspend fun estilos100(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Estilos",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "00:50.71",
                nombre = "Caeleb",
                apellido = "Dressel",
                fecha = "2021-07-31"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 100,
                estilo = "Estilos",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "00:55.48",
                nombre = "Sarah",
                apellido = "Sjostrom",
                fecha = "2016-08-07"
            )

        )
    }
    private suspend fun estilos200(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Estilos",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "01:55.22",
                nombre = "Ryan",
                apellido = "Lochte",
                fecha = "2011-07-28"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Estilos",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "01:49.63",
                nombre = "Ryan",
                apellido = "Lochte",
                fecha = "2010-12-12"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Estilos",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "02:06.12",
                nombre = "Katinka",
                apellido = "Hosszu",
                fecha = "2015-08-09"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 200,
                estilo = "Estilos",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "02:01.86",
                nombre = "Siobhan",
                apellido = "Haughey",
                fecha = "2021-10-30"
            )
        )
    }
    private suspend fun estilos400(pruebaDAO: PruebaDAO, recordDAO: WorldRecordDAO) {
        val idLargaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Estilos",
                genero = "Masc",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaMasc,
                tiempo = "04:03.84",
                nombre = "Michael",
                apellido = "Phelps",
                fecha = "2008-08-11"
            )
        )

        val idCortaMasc = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Estilos",
                genero = "Masc",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaMasc,
                tiempo = "03:55.50",
                nombre = "Dávid",
                apellido = "Verrasztó",
                fecha = "2021-12-20"
            )
        )

        val idLargaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Estilos",
                genero = "Fem",
                piscina = "50m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idLargaFem,
                tiempo = "04:26.36",
                nombre = "Katinka",
                apellido = "Hosszu",
                fecha = "2016-08-10"
            )
        )

        val idCortaFem = pruebaDAO.insertarPrueba(
            Prueba(
                distancia = 400,
                estilo = "Estilos",
                genero = "Fem",
                piscina = "25m"
            )
        )
        recordDAO.insert(
            WorldRecord(
                pruebaId = idCortaFem,
                tiempo = "04:21.40",
                nombre = "Katinka",
                apellido = "Hosszu",
                fecha = "2018-12-12"
            )
        )
    }


    private suspend fun insertarMisTiempos(misTieposService: MisTiemposService){
        misTieposService.insert(MisTiempos(0, "2026-01-08", "00:25.32", 1))
    }






}
