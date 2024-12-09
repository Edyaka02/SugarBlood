package com.edmalyon.sugarblood.di

import android.content.Context
import androidx.room.Room
import com.edmalyon.sugarblood.data.local.database.AppDatabase
import com.edmalyon.sugarblood.data.local.database.dao.GlucosaDao
import com.edmalyon.sugarblood.data.local.database.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//import android.content.Context
//import androidx.room.Room
//import com.edmalyon.sugarblood.data.local.database.AppDatabase
//import com.edmalyon.sugarblood.data.local.database.dao.GlucosaDao
//import com.edmalyon.sugarblood.data.local.database.dao.InformacionDao
//import com.edmalyon.sugarblood.data.local.database.dao.RecordatorioDao
//import com.edmalyon.sugarblood.data.local.database.dao.UsuarioDao
//import com.edmalyon.sugarblood.data.local.database.dao.UsuarioGlucosaDao
//import com.edmalyon.sugarblood.data.local.database.dao.UsuarioInformacionDao
//import com.edmalyon.sugarblood.data.local.database.dao.UsuarioRecordatorioDao
//import com.edmalyon.sugarblood.repository.GlucosaRepositorio
//import com.edmalyon.sugarblood.repository.RecordatorioRepositorio
//import com.edmalyon.sugarblood.repository.UsuarioRepositorio
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//
////    @Provides
////    @Singleton
////    fun proporcionarAppDatabase(@ApplicationContext context: Context): AppDatabase {
////        return Room.databaseBuilder(
////            context,
////            AppDatabase::class.java,
////            "db_sugarblood"
////        ).fallbackToDestructiveMigrationFrom().build()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarUsuarioDao(appDatabase: AppDatabase): UsuarioDao {
////        return appDatabase.usuarioDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarGlucosaDao(appDatabase: AppDatabase): GlucosaDao {
////        return appDatabase.glucosaDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarInformacionDao(appDatabase: AppDatabase): InformacionDao {
////        return appDatabase.informacionDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarRecordatorioDao(appDatabase: AppDatabase): RecordatorioDao {
////        return appDatabase.recordatorioDao()
////    }
////
////
////    @Provides
////    @Singleton
////    fun proporcionarUsuarioRecordatorioDao(appDatabase: AppDatabase): UsuarioRecordatorioDao {
////        return appDatabase.usuarioRecordatorioDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarUsuarioInformacionDao(appDatabase: AppDatabase): UsuarioInformacionDao {
////        return appDatabase.usuarioInformacionDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarUsuarioGlucosaDao(appDatabase: AppDatabase): UsuarioGlucosaDao {
////        return appDatabase.usuarioGlucosaDao()
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarUsuarioRepository(usuarioDao: UsuarioDao,
////                                      informacionDao: InformacionDao,
////                                      usuarioInformacionDao: UsuarioInformacionDao): UsuarioRepositorio {
////        return UsuarioRepositorio(usuarioDao, informacionDao, usuarioInformacionDao)
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarGlucosaRepository(glucosaDao: GlucosaDao,
////                                       usuarioGlucosaDao: UsuarioGlucosaDao): GlucosaRepositorio {
////        return GlucosaRepositorio(glucosaDao, usuarioGlucosaDao)
////    }
////
////    @Provides
////    @Singleton
////    fun proporcionarRecordatorioRepository(recordatorioDao: RecordatorioDao,
////                                            usuarioRecordatorioDao: UsuarioRecordatorioDao): RecordatorioRepositorio {
////        return RecordatorioRepositorio(recordatorioDao, usuarioRecordatorioDao)
////    }
//}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proveer la instancia de RoomDatabase
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db_sugarblood"
        ).fallbackToDestructiveMigration() // Esto elimina y recrea la base de datos
            .build()
    }

    // Proveer UsuarioDao
    @Provides
    fun provideUsuarioDao(appDatabase: AppDatabase): UsuarioDao {
        return appDatabase.usuarioDao()
    }

    @Provides
    fun provideGlucosaDao(appDatabase: AppDatabase): GlucosaDao {
        return appDatabase.glucosaDao()
    }
}