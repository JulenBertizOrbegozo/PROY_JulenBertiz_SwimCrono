package com.julen.swimcrono.model.entity

import android.R
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
class Usuario (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "apellido1") val apellido1: String?,
    @ColumnInfo(name = "apellido2") val apellido2: String?,
    @ColumnInfo(name = "correo") val correo: String,
    @ColumnInfo(name = "contraseina") val contraseina: String,
    @ColumnInfo(name = "fecha_nacimiento") val fecha_nacimiento: String?,
    @ColumnInfo(name = "genero") val genero: String?,
    @ColumnInfo(name = "foto") val foto: String?,
    @ColumnInfo(name = "activo") var activo: Boolean


)
