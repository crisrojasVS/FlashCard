package com.cristo.flashcard.Presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cristo.flashcard.Presentation.components.BotonContorno
import com.cristo.flashcard.Presentation.components.BotonDegradado
import com.cristo.flashcard.Presentation.components.Encabezado
import com.cristo.flashcard.Presentation.components.Etiqueta
import com.cristo.flashcard.Presentation.viewmodels.FlashcardUiState
import com.cristo.flashcard.R

@Composable
fun EstudioScreen(
    estado: FlashcardUiState,
    onVoltear: () -> Unit,
    onAnterior: () -> Unit,
    onSiguiente: () -> Unit,
    onVolver: () -> Unit
) {
    val tarjeta = estado.tarjetas.getOrNull(estado.indiceActual) ?: return
    val total = estado.tarjetas.size
    val blanco = colorResource(R.color.blanco)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.fondo))
    ) {
        Encabezado(
            titulo = stringResource(R.string.titulo_estudio),
            subtitulo = stringResource(R.string.progreso, estado.indiceActual + 1, total),
            onAtras = onVolver
        ) {
            LinearProgressIndicator(
                progress = { (estado.indiceActual + 1).toFloat() / total },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = blanco,
                trackColor = blanco.copy(alpha = 0.3f)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 320.dp)
                    .shadow(8.dp, RoundedCornerShape(28.dp))
                    .background(blanco, RoundedCornerShape(28.dp))
                    .clip(RoundedCornerShape(28.dp))
                    .clickable(onClick = onVoltear)
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically)
            ) {
                if (estado.mostrarRespuesta) {
                    Etiqueta(
                        texto = stringResource(R.string.etiqueta_respuesta),
                        fondo = colorResource(R.color.chip_mia_fondo),
                        colorTexto = colorResource(R.color.chip_mia_texto)
                    )
                    Text(
                        text = tarjeta.respuesta,
                        color = colorResource(R.color.texto),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Etiqueta(
                        texto = stringResource(R.string.etiqueta_pregunta),
                        fondo = colorResource(R.color.chip_base_fondo),
                        colorTexto = colorResource(R.color.chip_base_texto)
                    )
                    Text(
                        text = tarjeta.pregunta,
                        color = colorResource(R.color.texto),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = stringResource(R.string.pista_voltear),
                        color = colorResource(R.color.texto_suave),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                BotonContorno(
                    texto = stringResource(R.string.boton_anterior),
                    onClick = onAnterior,
                    modifier = Modifier.weight(1f)
                )
                BotonDegradado(
                    texto = stringResource(R.string.boton_siguiente),
                    onClick = onSiguiente,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.boton_volver_lista),
                    color = colorResource(R.color.texto_suave),
                    fontSize = 16.sp
                )
            }
        }
    }
}