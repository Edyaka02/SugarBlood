package com.edmalyon.sugarblood.data.local.database.repository

import android.database.sqlite.SQLiteConstraintException
import com.edmalyon.sugarblood.data.local.database.dao.UsuarioDao
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val usuarioDao: UsuarioDao
) {
    // Obtener un usuario por ID
    suspend fun obtenerUsuarioPorId(id_usuario: Int): Usuario? {
        return usuarioDao.obtener(id_usuario)
    }

    suspend fun iniciarSesion(username: String, password: String): Result<Usuario> {
        return try {
            val usuario =
                usuarioDao.obtenerNombre(username)
            if (usuario != null && usuario.password_usuario == password) {
                Result.success(usuario)
            } else {
                Result.failure(Exception("Credenciales incorrectas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun insertarUsuario(usuario: Usuario): Result<Unit> {
        return try {
            usuarioDao.insertar(usuario)
            Result.success(Unit)
        } catch (e: SQLiteConstraintException) {
            Result.failure(e)
        }
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