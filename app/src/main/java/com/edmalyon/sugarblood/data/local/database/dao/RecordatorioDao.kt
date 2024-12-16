package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.edmalyon.sugarblood.data.local.database.entities.Recordatorio

@Dao
interface RecordatorioDao {
    //-------------------------------------------------------------------------------------------
    // Nuevas funciones
    //-------------------------------------------------------------------------------------------

    @Insert
    suspend fun insertar(recordatorio: Recordatorio) : Long

    @Update
    suspend fun actualizar(recordatorio: Recordatorio)

    @Query("SELECT * FROM recordatorio WHERE id_recordatorio = :id_recordatorio")
    suspend fun obtener(id_recordatorio: Int): Recordatorio?

    @Query("SELECT * FROM recordatorio")
    suspend fun obtenerTodos(): List<Recordatorio>

    @Query("SELECT * FROM recordatorio WHERE id_usuario = :id_usuario")
    suspend fun obtenerPorUsuario(id_usuario: Int): List<Recordatorio>

    @Delete
    suspend fun borrar(recordatorio: Recordatorio)
}