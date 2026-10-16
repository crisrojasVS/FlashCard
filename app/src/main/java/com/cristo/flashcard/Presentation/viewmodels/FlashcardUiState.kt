package com.cristo.flashcard.Presentation.viewmodels

import com.cristo.flashcard.data.Flashcard

data class FlashcardUiState(
    val tarjetas: List<Flashcard> = emptyList(),
    val pantalla: screen = screen.LISTA,
    val indiceActual: Int = 0,
    val mostrarRespuesta: Boolean = false,
    val mostrarError: Boolean = false
)