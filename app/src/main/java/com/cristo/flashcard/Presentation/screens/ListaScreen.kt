package com.cristo.flashcard.Presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cristo.flashcard.Presentation.components.BotonDegradado
import com.cristo.flashcard.Presentation.components.BotonFlotante
import com.cristo.flashcard.Presentation.components.Encabezado
import com.cristo.flashcard.Presentation.components.Etiqueta
import com.cristo.flashcard.Presentation.components.TarjetaItem
import com.cristo.flashcard.Presentation.viewmodels.FlashcardUiState
import com.cristo.flashcard.R

@Composable
fun ListaScreen(
    estado: FlashcardUiState,
    onEstudiar: () -> Unit,
    onNueva: () -> Unit,
    onEliminar: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.fondo))
    ) {
        Column {
            Encabezado(
                titulo = stringResource(R.string.titulo_lista),
                subtitulo = stringResource(R.string.cantidad_tarjetas, estado.tarjetas.size)
            ) {

            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 22.dp,
                    bottom = 100.dp
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    BotonDegradado(
                        texto = stringResource(R.string.boton_estudiar),
                        onClick = onEstudiar,
                        icono = Icons.Default.PlayArrow
                    )
                }
                items(estado.tarjetas, key = { it.id }) { tarjeta ->
                    TarjetaItem(
                        tarjeta = tarjeta,
                        onEliminar = { onEliminar(tarjeta.id) }
                    )
                }
            }
        }

        BotonFlotante(
            onClick = onNueva,
            descripcion = stringResource(R.string.agregar_tarjeta),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(20.dp)
        )
    }
}