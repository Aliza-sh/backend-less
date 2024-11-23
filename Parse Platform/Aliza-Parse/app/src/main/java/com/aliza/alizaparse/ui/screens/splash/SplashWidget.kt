package com.aliza.alizaparse.ui.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliza.alizaparse.R
import kotlinx.coroutines.delay

@Composable
fun SplashWidget(
    modifier: Modifier = Modifier,
    onNavigateTo: () -> Unit
) {
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    // انیمیشن برای تغییر مقیاس لوگو
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
        )
        delay(500) // تاخیر قبل از رفتن به صفحه اصلی
        onNavigateTo()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // دایره‌های گرافیکی به عنوان انیمیشن یا المان تزئینی
        Box(
            modifier = Modifier
                .size((200.dp * scale.value)) // مقیاس‌پذیری دایره
                .background(Color.Gray.copy(0.1f), shape = CircleShape) // دایره‌ای سفید
                .align(Alignment.Center)
        )

        // لوگوی اپ
        Image(
            painter = painterResource(id = R.drawable.logo_app), // لوگوی اپ شما
            contentDescription = "App Logo",
            modifier = Modifier
                .size(170.dp)
                .padding(end = 8.dp)
                .alpha(alpha.value) // شفافیت لوگو
                .align(Alignment.Center)
        )
    }
}