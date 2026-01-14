package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.UsuarioDAO
import com.julen.swimcrono.model.entity.Usuario
import java.security.MessageDigest

class UsuarioService(private val usuarioDao: UsuarioDAO) {

    suspend fun insertarUsuario(usuario: Usuario) {
        usuarioDao.insertAll(usuario)
    }

    suspend fun borrarUsuario(usuario: Usuario) {
        usuarioDao.delete(usuario)
    }

    suspend fun getAllUsuarios(): List<Usuario> {
        return usuarioDao.getAll()
    }

    suspend fun getUsuarioById(id: Long): Usuario? {
        return usuarioDao.getById(id).firstOrNull()
    }

    suspend fun getUsuarioByMailAndPass(correo: String, contrasena: String): Usuario? {
        return usuarioDao.getUserByMailAndPasswd(correo, contrasena)
    }
    suspend fun getUsuarioByMail(correo: String): Usuario?{
        return usuarioDao.getUsuarioByMail(correo)
    }
    suspend fun updateUser(user:Usuario){
        usuarioDao.update(user)
    }
    suspend fun getUserActivo(): Usuario{
        return usuarioDao.getUserActivo()
    }

    fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) } // Convierte a hexadecimal
    }
}