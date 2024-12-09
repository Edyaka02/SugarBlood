package com.edmalyon.sugarblood.data.local.database.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import com.edmalyon.sugarblood.data.local.database.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    // MutableState para el usuario
    private val _usuario = mutableStateOf<Usuario?>(null)
    val usuario: State<Usuario?> get() = _usuario

    // Función para obtener un usuario por ID
    fun obtenerUsuarioPorId(id: Int) {
        viewModelScope.launch {
            // Obtener usuario de la base de datos (puedes usar tu repository para esto)
            val usuarioObtenido = usuarioRepository.obtenerUsuarioPorId(id)
            _usuario.value = usuarioObtenido
        }
    }

    // Función para insertar un usuario
    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                usuarioRepository.insertarUsuario(usuario)
            } catch (e: Exception) {
                Log.e("UsuarioViewModel", "Error registrando usuario: ${e.message}")
            }
        }
    }


//    fun insertarUsuario(usuario: Usuario) {
//        viewModelScope.launch {
//            usuarioRepositorio.insertarUsuario(usuario)
//        }
//    }
//
//    fun actualizarUsuario(usuario: Usuario) {
//        viewModelScope.launch {
//            usuarioRepositorio.actualizarUsuario(usuario)
//        }
//    }
//
//    fun borrarUsuario(usuario: Usuario) {
//        viewModelScope.launch {
//            usuarioRepositorio.borrarUsuario(usuario)
//        }
//    }
//
//    fun obtenerUsuarioPorId(id_usuario: Int){
//        viewModelScope.launch {
//            usuarioRepositorio.obtenerUsuarioPorId(id_usuario)
//        }
//    }
}