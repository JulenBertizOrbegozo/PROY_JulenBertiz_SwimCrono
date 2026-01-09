package com.julen.swimcrono.model.relations

import com.julen.swimcrono.model.entity.MisTiempos
import com.julen.swimcrono.model.entity.Prueba

data class TiempoConPrueba(
    val tiempo: MisTiempos,
    val prueba: Prueba
)
