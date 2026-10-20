package com.cristo.flashcard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first

class FlashcardRepository(private val dataSource: FlashcardDataSource) {

    val tarjetas: Flow<List<Flashcard>> = combine(
        dataSource.leerUsuario(),
        dataSource.leerEliminadas()
    ) { delUsuario, eliminadas ->
        dataSource.tarjetasSemilla().filter { it.id !in eliminadas } + delUsuario
    }

    suspend fun agregarTarjeta(pregunta: String, respuesta: String) {
        val delUsuario = dataSource.leerUsuario().first()
        val nuevoId = (delUsuario.maxOfOrNull { it.id } ?: 4) + 1
        val nueva = Flashcard(
            id = nuevoId,
            pregunta = pregunta,
            respuesta = respuesta,
            esBase = false
        )
        dataSource.guardarUsuario(delUsuario + nueva)
    }

    suspend fun eliminarTarjeta(id: Int) {
        val esSemilla = dataSource.tarjetasSemilla().any { it.id == id }
        if (esSemilla) {
            val eliminadas = dataSource.leerEliminadas().first()
            dataSource.guardarEliminadas(eliminadas + id)
        } else {
            val delUsuario = dataSource.leerUsuario().first()
            dataSource.guardarUsuario(delUsuario.filter { it.id != id })
        }
    }
}