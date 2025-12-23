package com.julen.swimcrono.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.julen.swimcrono.model.entity.Usuario

@Dao
interface UsuarioDAO {
    @Query("select * from usuario")
    fun getAll(): List<Usuario>

    @Query("select * from usuario where id = :usuarioId")
    fun getById(usuarioId : Long) : List<Usuario>

    @Insert
    fun insertAll(vararg usuario: Usuario)

    @Delete
    fun delete(usuario:Usuario)
}