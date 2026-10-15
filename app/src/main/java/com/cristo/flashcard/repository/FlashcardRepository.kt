package com.cristo.flashcard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class FlashcardRepository(private val dataSource: FlashcardDataSource) {

    val tarjetas: Flow<List<Flashcard>> = dataSource.leerUsuario().map { delUsuario ->
        dataSource.tarjetasSemilla() + delUsuario
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
}