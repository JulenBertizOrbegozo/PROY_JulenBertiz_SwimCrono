package com.julen.swimcrono.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prueba")
data class Prueba(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "distancia")
    val distancia: Int,

    @ColumnInfo(name = "estilo")
    val estilo: String,

    @ColumnInfo(name = "genero")
    val genero: String,

    @ColumnInfo(name = "piscina")
    val piscina: String
)
