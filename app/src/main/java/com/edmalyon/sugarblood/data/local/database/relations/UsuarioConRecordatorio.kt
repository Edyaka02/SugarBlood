package com.edmalyon.sugarblood.data.local.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.edmalyon.sugarblood.data.local.database.entities.Recordatorio
import com.edmalyon.sugarblood.data.local.database.entities.Usuario

data class UsuarioConRecordatorio(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "id_usuario",
        entityColumn = "id_usuario"
    )
    val recordatorio: List<Recordatorio>
)
