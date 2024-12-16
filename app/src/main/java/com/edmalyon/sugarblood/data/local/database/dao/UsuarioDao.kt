package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import com.edmalyon.sugarblood.data.local.database.relations.UsuarioConGlucosa
import com.edmalyon.sugarblood.data.local.database.relations.UsuarioConInformacion
import com.edmalyon.sugarblood.data.local.database.relations.UsuarioConRecordatorio

@Dao
interface UsuarioDao {
    //-------------------------------------------------------------------------------------------//
    // NUEVAS FUNCIONES
    //-------------------------------------------------------------------------------------------//

    //-------------------------------------------------------------------------------------------
    // Solo usuarios
    //-------------------------------------------------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(usuario: Usuario) : Long

    @Update
    suspend fun actualizar(usuario: Usuario)

    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun obtener(id_usuario: Int): Usuario?

    @Query("SELECT * FROM usuario WHERE nombre_usuario = :nombre_usuario")
    suspend fun obtenerNombre(nombre_usuario: String): Usuario?

    @Query("SELECT * FROM usuario")
    suspend fun obtenerTodos(): List<Usuario>

    @Delete
    suspend fun borrar(usuario: Usuario)

    //-------------------------------------------------------------------------------------------
    // Usuarios con glucosa
    //-------------------------------------------------------------------------------------------

    @Transaction
    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun obtenerUsuarioConGlucosa(id_usuario: Int): UsuarioConGlucosa?

    //-------------------------------------------------------------------------------------------
    // Usuarios con informacion
    //-------------------------------------------------------------------------------------------

    @Transaction
    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun obtenerUsuarioConInformacion(id_usuario: Int): UsuarioConInformacion?

    //-------------------------------------------------------------------------------------------
    // Usuarios con recordatorio
    //-------------------------------------------------------------------------------------------

    @Transaction
    @Query("SELECT * FROM usuario WHERE id_usuario = :id_usuario")
    suspend fun obtenerUsuarioConGlucosaYInformacion(id_usuario: Int): UsuarioConRecordatorio?







}