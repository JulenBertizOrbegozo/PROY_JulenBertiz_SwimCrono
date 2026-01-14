package com.julen.swimcrono.model.service

import com.julen.swimcrono.model.dao.UsuarioDAO
import com.julen.swimcrono.model.entity.Usuario

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
}