package com.edmalyon.sugarblood.data.local.database.repository

import com.edmalyon.sugarblood.data.local.database.dao.UsuarioDao
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val usuarioDao: UsuarioDao
) {
    // Obtener un usuario por ID
    suspend fun obtenerUsuarioPorId(id_usuario: Int): Usuario? {
        return usuarioDao.obtener(id_usuario)
    }

    // Insertar un usuario
    suspend fun insertarUsuario(usuario: Usuario) {
        usuarioDao.insertar(usuario)
    }

    // Actualizar un usuario
    suspend fun actualizarUsuario(usuario: Usuario) {
        usuarioDao.actualizar(usuario)
    }

    // Eliminar un usuario
    suspend fun borrarUsuario(usuario: Usuario) {
        usuarioDao.borrar(usuario)
    }
}