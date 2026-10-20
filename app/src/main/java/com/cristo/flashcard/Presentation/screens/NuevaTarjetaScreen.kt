package com.cristo.flashcard.Presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.flashcard.Presentation.components.BotonDegradado
import com.cristo.flashcard.Presentation.components.CampoTexto
import com.cristo.flashcard.Presentation.components.Encabezado
import com.cristo.flashcard.R

@Composable
fun NuevaTarjetaScreen(
    mostrarError: Boolean,
    onGuardar: (String, String) -> Unit,
    onCancelar: () -> Unit
) {
    var pregunta by rememberSaveable { mutableStateOf("") }
    var respuesta by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.fondo))
            .imePadding()
    ) {
        Encabezado(
            titulo = stringResource(R.string.titulo_nueva),
            subtitulo = stringResource(R.string.subtitulo_nueva),
            onAtras = onCancelar
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(24.dp))
                    .background(colorResource(R.color.blanco), RoundedCornerShape(24.dp))
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                CampoTexto(
                    etiqueta = stringResource(R.string.campo_pregunta),
                    valor = pregunta,
                    onCambio = { pregunta = it }
                )
                CampoTexto(
                    etiqueta = stringResource(R.string.campo_respuesta),
                    valor = respuesta,
                    onCambio = { respuesta = it },
                    lineas = 4
                )
                if (mostrarError) {
                    Text(
                        text = stringResource(R.string.error_campos_vacios),
                        color = colorResource(R.color.error),
                        fontSize = 14.sp
                    )
                }
            }

            BotonDegradado(
                texto = stringResource(R.string.boton_guardar),
                onClick = { onGuardar(pregunta, respuesta) },
                icono = Icons.Default.Check
            )

            TextButton(
                onClick = onCancelar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.boton_cancelar),
                    color = colorResource(R.color.texto_suave),
                    fontSize = 16.sp
                )
            }
        }
    }
}