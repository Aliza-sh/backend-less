package com.aliza.alizaparse.ui.screens.verify

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aliza.alizaparse.R
import com.aliza.alizaparse.ui.component.LoadingButton
import kotlinx.coroutines.delay

@Composable
fun TimerButton(
    modifier: Modifier = Modifier,
    initialTime: Int = 30,
    login: Boolean,
    onResendClicked: () -> Unit
) {
    var timeLeft by remember { mutableIntStateOf(initialTime) }
    var isResendState by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = timeLeft.toFloat() / initialTime.toFloat(),
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val timerDuration = initialTime * 1000L


    LaunchedEffect(timeLeft) {
        if (timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else {
            isResendState = true
        }
    }

    LoadingButton(
        onClick = {
            if (isResendState) {
                isResendState = false
                timeLeft = initialTime
                onResendClicked()
            }
        },
        loading = login,
        enabled = if (login || !isResendState) false else true,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
        ),
        contentPadding = PaddingValues()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .animateContentSize()
            )

            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isResendState)
                            R.drawable.ic_refresh
                        else
                            R.drawable.ic_clock
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(28.dp)
                        .padding(end = 8.dp),
                )
                Text(
                    text = if (isResendState) "Resend Code" else "$timeLeft seconds",
                )
            }
        }
    }
}