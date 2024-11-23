package com.aliza.alizaparse.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.aliza.alizaparse.ui.theme.Green
import com.aliza.alizaparse.ui.theme.GreenDark
import com.aliza.alizaparse.utils.random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NightSky(
    modifier: Modifier = Modifier.fillMaxSize(),
    durationInSeconds: Int = 3,
    isAnimated: Boolean = false,
    starCount: Int = 100,
) {
    var starProperties by remember { mutableStateOf(listOf<Triple<Float, Float, Float>>()) }
    val alphaAnimatables = remember { List(starCount) { Animatable((0f..1f).random()) } }

    LaunchedEffect(isAnimated) {
        while (isAnimated) {
            alphaAnimatables.forEachIndexed { index, animatable ->
                val randomTargetAlpha = (0f..1f).random()
                val randomDuration = (300..1200).random()
                launch {
                    animatable.animateTo(
                        targetValue = randomTargetAlpha,
                        animationSpec = tween(durationMillis = randomDuration)
                    )
                }
            }
            delay(durationInSeconds * 1000L)
        }
    }

    Canvas(
        modifier = modifier
    ) {
        if (starProperties.isEmpty()) {
            starProperties = generateStarProperties(starCount, size.width, size.height)
        }
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(GreenDark, Green),
                startY = 0f,
                endY = size.height
            )
        )
        for (i in 1..starCount) {
            val (randomX, randomY, starSize) = starProperties[i - 1]
            val alpha = alphaAnimatables[i - 1].value  // آلفای انیمیشنی

            drawCircle(
                color = Color.White.copy(alpha = alpha),
                radius = starSize,
                center = Offset(randomX, randomY)
            )
        }
    }
}

fun generateStarProperties(
    count: Int,
    width: Float,
    height: Float
): List<Triple<Float, Float, Float>> {
    return List(count) {
        val randomX = (0f..width).random()
        val randomY = (0f..height).random()
        val starSize = (0.5f..5f).random()
        Triple(randomX, randomY, starSize)
    }
}