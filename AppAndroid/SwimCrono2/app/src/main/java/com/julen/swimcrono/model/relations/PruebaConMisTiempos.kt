package com.julen.swimcrono.model.relations
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.MisTiempos

import androidx.room.Embedded
import androidx.room.Relation

data class PruebaConMisTiempos(
    @Embedded val prueba: Prueba,

    @Relation(
        parentColumn = "id",
        entityColumn = "prueba_id"
    )
    val misTiempos: List<MisTiempos>
)
