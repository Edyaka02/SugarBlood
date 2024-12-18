package com.edmalyon.sugarblood.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edmalyon.sugarblood.data.local.database.dao.GlucosaDao
import com.edmalyon.sugarblood.data.local.database.dao.InformacionDao
import com.edmalyon.sugarblood.data.local.database.dao.RecordatorioDao
import com.edmalyon.sugarblood.data.local.database.dao.UsuarioDao
import com.edmalyon.sugarblood.data.local.database.entities.Glucosa
import com.edmalyon.sugarblood.data.local.database.entities.Informacion
import com.edmalyon.sugarblood.data.local.database.entities.Recordatorio
import com.edmalyon.sugarblood.data.local.database.entities.Usuario

@Database(
    entities = [
        Recordatorio::class,
        Glucosa::class,
        Informacion::class,
        Usuario::class
    ],
    version = 5,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun recordatorioDao(): RecordatorioDao
    abstract fun glucosaDao(): GlucosaDao
    abstract fun informacionDao(): InformacionDao
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun iniciarBaseDeDatos(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_sugarblood"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}