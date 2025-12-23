package com.julen.swimcrono.model.relations
import com.julen.swimcrono.model.entity.Prueba
import com.julen.swimcrono.model.entity.WorldRecord

import androidx.room.Embedded
import androidx.room.Relation

data class PruebaConWorldRecord(
    @Embedded val prueba: Prueba,

    @Relation(
        parentColumn = "id",
        entityColumn = "prueba_id"
    )
    val worldRecord: WorldRecord?
)
