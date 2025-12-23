package com.julen.swimcrono.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "mis_tiempos",
    foreignKeys = [
        ForeignKey(
            entity = Prueba::class,
            parentColumns = ["id"],
            childColumns = ["prueba_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["prueba_id"])])
class MisTiempos(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "fecha")
    val fecha: String,

    @ColumnInfo(name = "tiempo")
    val tiempo: String,

    @ColumnInfo(name = "prueba_id")
    val pruebaId: Long
)