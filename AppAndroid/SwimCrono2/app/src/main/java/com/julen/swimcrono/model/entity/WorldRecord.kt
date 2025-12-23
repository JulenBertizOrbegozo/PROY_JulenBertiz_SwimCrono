package com.julen.swimcrono.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "world_record",
    foreignKeys = [
        ForeignKey(
            entity = Prueba::class,
            parentColumns = ["id"],
            childColumns = ["prueba_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["prueba_id"])])
data class WorldRecord (
    @PrimaryKey
    @ColumnInfo(name = "prueba_id")
    val pruebaId: Long,

    @ColumnInfo(name = "tiempo")
    val tiempo : String,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "apellido")
    val apellido: String,
    @ColumnInfo(name = "fecha")
    val fecha: String
)