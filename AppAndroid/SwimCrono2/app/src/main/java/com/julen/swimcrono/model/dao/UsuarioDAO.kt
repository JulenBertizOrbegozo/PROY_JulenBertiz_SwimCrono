package com.julen.swimcrono.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.julen.swimcrono.model.entity.Usuario

@Dao
interface UsuarioDAO {
    @Query("select * from usuario")
    suspend fun getAll(): List<Usuario>

    @Query("select * from usuario where id = :usuarioId")
    suspend fun getById(usuarioId : Long) : List<Usuario>

    @Insert
    suspend fun insertAll(vararg usuario: Usuario)

    @Delete
    suspend fun delete(usuario:Usuario)

    @Query("select * from usuario where correo = :correo and contraseina = :contraseina limit 1")
    suspend fun getUserByMailAndPasswd(correo:String, contraseina :String) : Usuario?

    @Update
    suspend fun update(usuario: Usuario)

    @Query("select * from usuario where correo =:correo")
    suspend fun getUsuarioByMail(correo: String): Usuario?

    @Query("select * from usuario where activo = true limit 1")
    suspend fun getUserActivo(): Usuario
}