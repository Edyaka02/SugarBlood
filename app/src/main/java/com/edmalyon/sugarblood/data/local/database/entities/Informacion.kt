package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "informacion",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id_usuario"],
        childColumns = ["id_usuario"],
        onDelete = ForeignKey.CASCADE
    )],
    //indices = [Index(value = ["id_usuario"])]
)
data class Informacion(
    @PrimaryKey(autoGenerate = true) val id_informacion: Int,
    val nombre_informacion: String,
    val apellido_informacion: String,
    val altura_informacion: Double,
    val edad_informacion: Int,
    val sexo_informacion: String,
    val id_usuario: Int
)
