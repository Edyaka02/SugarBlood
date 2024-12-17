package com.edmalyon.sugarblood.data.local.database.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edmalyon.sugarblood.data.local.database.entities.Usuario
import com.edmalyon.sugarblood.data.local.database.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    // MutableState para el usuario
    private val _usuario = mutableStateOf<Usuario?>(null)
    val usuario: State<Usuario?> get() = _usuario

    //LiveData para resultados de inserción
    private val _insercionResultado = MutableLiveData<Result<Long>>()
    val insercionResultado: LiveData<Result<Long>> get() = _insercionResultado

    private val _loginResult = MutableLiveData<Result<Usuario>>()
    val loginResult: LiveData<Result<Usuario>> get() = _loginResult

    // Estado de autenticación del usuario
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _usuarioId = MutableLiveData<Int>()
    val usuarioId: LiveData<Int> = _usuarioId

    // Función para obtener un usuario por ID
    fun obtenerUsuarioPorId(id: Int) {
        viewModelScope.launch {
            // Obtener usuario de la base de datos (puedes usar tu repository para esto)
            val usuarioObtenido = usuarioRepository.obtenerUsuarioPorId(id)
            _usuario.value = usuarioObtenido
        }
    }

    fun iniciarSesion(username: String, password: String) {
        viewModelScope.launch {
            val result =
                usuarioRepository.iniciarSesion(username, password)
            _loginResult.value = result

            // Si la autenticación es exitosa, actualizar el estado
            if (result.isSuccess) {
                val usuario = result.getOrNull()
                if (usuario != null) {
                    _usuarioId.value = usuario.id_usuario // Establecer el usuarioId
                    _isAuthenticated.value = true // Usuario autenticado
                } else {
                    _isAuthenticated.value = false // Autenticación fallida
                }
            } else {
                _isAuthenticated.value = false // Autenticación fallida
            }

        }
    }

    // Función para insertar un usuario
    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                //usuarioRepository.insertarUsuario(usuario)
                val resultado = usuarioRepository.insertarUsuario(usuario)
                _insercionResultado.value = resultado
                if (resultado.isSuccess) {
                    val usuarioId = resultado.getOrNull()
                    if (usuarioId != null) {
                        _usuarioId.value = usuarioId.toInt()
                        _isAuthenticated.value = true
                    } else
                        _isAuthenticated.value = false
                }
            } catch (e: Exception) {
                Log.e("UsuarioViewModel", "Error registrando usuario: ${e.message}")
                _insercionResultado.value = Result.failure(e)
                _isAuthenticated.value = false
            }
        }
    }

    // Función para cerrar sesión
    fun logout() {
        _isAuthenticated.value = false // Restablecer el estado de autenticación
    }
}