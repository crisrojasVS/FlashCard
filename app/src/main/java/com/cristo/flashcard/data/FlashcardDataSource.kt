package com.cristo.flashcard.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cristo.flashcard.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject

private val Context.dataStore by preferencesDataStore(name = "flashcards")

class FlashcardDataSource(private val context: Context) {

    private val claveTarjetas = stringPreferencesKey("tarjetas_usuario")

    fun tarjetasSemilla(): List<Flashcard> {
        return listOf(
            Flashcard(
                id = 1,
                pregunta = context.getString(R.string.semilla_1_pregunta),
                respuesta = context.getString(R.string.semilla_1_respuesta),
                esBase = true
            ),
            Flashcard(
                id = 2,
                pregunta = context.getString(R.string.semilla_2_pregunta),
                respuesta = context.getString(R.string.semilla_2_respuesta),
                esBase = true
            ),
            Flashcard(
                id = 3,
                pregunta = context.getString(R.string.semilla_3_pregunta),
                respuesta = context.getString(R.string.semilla_3_respuesta),
                esBase = true
            ),
            Flashcard(
                id = 4,
                pregunta = context.getString(R.string.semilla_4_pregunta),
                respuesta = context.getString(R.string.semilla_4_respuesta),
                esBase = true
            )
        )
    }

    fun leerUsuario(): Flow<List<Flashcard>> {
        return context.dataStore.data.map { preferencias ->
            val texto = preferencias[claveTarjetas] ?: "[]"
            convertirALista(texto)
        }
    }

    suspend fun guardarUsuario(lista: List<Flashcard>) {
        context.dataStore.edit { preferencias ->
            preferencias[claveTarjetas] = convertirATexto(lista)
        }
    }

    private fun convertirATexto(lista: List<Flashcard>): String {
        val arreglo = JSONArray()
        for (tarjeta in lista) {
            val objeto = JSONObject()
            objeto.put("id", tarjeta.id)
            objeto.put("pregunta", tarjeta.pregunta)
            objeto.put("respuesta", tarjeta.respuesta)
            arreglo.put(objeto)
        }
        return arreglo.toString()
    }

    private fun convertirALista(texto: String): List<Flashcard> {
        val arreglo = JSONArray(texto)
        val lista = mutableListOf<Flashcard>()
        for (i in 0 until arreglo.length()) {
            val objeto = arreglo.getJSONObject(i)
            lista.add(
                Flashcard(
                    id = objeto.getInt("id"),
                    pregunta = objeto.getString("pregunta"),
                    respuesta = objeto.getString("respuesta"),
                    esBase = false
                )
            )
        }
        return lista
    }
}