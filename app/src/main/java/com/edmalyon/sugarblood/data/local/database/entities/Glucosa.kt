package com.edmalyon.sugarblood.data.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "glucosa",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id_usuario"],
        childColumns = ["id_usuario"],
        onDelete = ForeignKey.CASCADE
    )],
    //indices = [Index(value = ["id_usuario"])]
)
data class Glucosa(
    @PrimaryKey(autoGenerate = true) val id_glucosa: Int = 0,
    val tiempo_glucosa: Long,
    val momento_glucosa: String?,
    val valor_glucosa: Float,
    val peso_glucosa: Float?,
    val nota_glucosa: String?,
    val id_usuario: Int
)
