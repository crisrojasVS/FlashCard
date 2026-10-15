package com.cristo.flashcard.data

data class Flashcard(
    val id: Int,
    val pregunta: String,
    val respuesta: String,
    val esBase: Boolean
)