package com.edmalyon.sugarblood.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa

@Dao
interface GlucosaDao {
    @Insert
    suspend fun insert(glucosa: Glucosa)

    @Query("SELECT * FROM glucosa WHERE id_usuario = :id_usuario ORDER BY tiempo_glucosa DESC")
    suspend fun getGlucosa(id_usuario: Int): List<Glucosa>

    @Update
    suspend fun update(glucosa: Glucosa)

    @Delete
    suspend fun delete(glucosa: Glucosa)

    // Nuevas funciones

    @Insert
    suspend fun insertar(glucosa: Glucosa) : Long

    @Update
    suspend fun actualizar(glucosa: Glucosa)

    @Query("SELECT * FROM glucosa WHERE id_glucosa = :id_glucosa")
    suspend fun obtener(id_glucosa: Int): Glucosa?

    @Query("SELECT * FROM glucosa")
    suspend fun obtenerTodos(): List<Glucosa>

    @Query("SELECT * FROM glucosa WHERE id_usuario = :id_usuario")
    suspend fun obtenerPorUsuario(id_usuario: Int): List<Glucosa>

    @Delete
    suspend fun borrar(glucosa: Glucosa)
}