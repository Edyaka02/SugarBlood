package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "recordatorio",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id_usuario"],
        childColumns = ["id_usuario"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["id_usuario"])]
)
data class Recordatorio(
    @PrimaryKey(autoGenerate = true) val id_recordatorio: Int = 0,
    val tiempo_recordatorio: Long,
    val tipo_recordatorio: String,
    val descripcion_recordatorio: String,
    val id_usuario: Int
)
