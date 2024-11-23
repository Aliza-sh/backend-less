package com.aliza.alizaparse.ui.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliza.alizaparse.R
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun HeaderSignWidget(modifier: Modifier = Modifier) {

    val rectOffsetY = remember { Animatable(-500f) }
    LaunchedEffect(Unit) {
        delay(500)

        rectOffsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )

    }

    Box {

        val curvedBottomShape = GenericShape { size, _ ->
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(0f, size.height * 0.6f)
                quadraticTo(
                    size.width / 2f, size.height + 50f,
                    size.width, size.height * 0.6f
                )
                lineTo(size.width, 0f)
                close()
            }
            addPath(path)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .offset(y = rectOffsetY.value.dp)
                .align(Alignment.TopCenter)
                .clip(curvedBottomShape)
        ) { NightSky() }

        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .offset(y = rectOffsetY.value.dp)
                .background(Color.White.copy(0.7f), shape = CircleShape)
                .padding(8.dp)
        )
    }

}