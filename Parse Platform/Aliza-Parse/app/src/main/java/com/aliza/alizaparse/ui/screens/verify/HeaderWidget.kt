package com.aliza.alizaparse.ui.screens.verify

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliza.alizaparse.R
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun HeaderWidget(modifier: Modifier = Modifier) {

    val rectOffsetY = remember { Animatable(-500f) }
    LaunchedEffect(Unit) {
        delay(500)

        rectOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

    }

    Image(
        painter = painterResource(id = R.drawable.logo_app),
        contentDescription = "App Logo",
        modifier = modifier
            .size(100.dp)
            .offset(y = rectOffsetY.value.dp)
            .background(Color.White.copy(0.7f), shape = CircleShape)
            .padding(8.dp)
    )
}