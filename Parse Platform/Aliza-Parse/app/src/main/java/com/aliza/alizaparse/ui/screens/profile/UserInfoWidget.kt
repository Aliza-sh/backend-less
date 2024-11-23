package com.aliza.alizaparse.ui.screens.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.aliza.alizaparse.R

@Composable
fun UserInfoWidget(
    profilePic: Uri?, onProfilePicChange: () -> Unit,
    firstName: String, onFirstNameChange: (String) -> Unit,
    lastName: String, onLastNameChange: (String) -> Unit,
    userName: String, onUserNameChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit,
    loadingPic: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 28.dp, start = 28.dp, top = 200.dp)
    ) {

        val (image, column) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(
                    24.dp,
                    shape = RoundedCornerShape(16.dp),
                    spotColor = MaterialTheme.colorScheme.primary
                )
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(top = 90.dp, bottom = 16.dp)
                .constrainAs(column) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            UserFormWidget(
                firstName = firstName, onFirstNameChange = onFirstNameChange,
                lastName = lastName, onLastNameChange = onLastNameChange,
                userName = userName, onUserNameChange = onUserNameChange,
                email = email, onEmailChange = onEmailChange,
            )
        }

        Box(
            modifier = Modifier
                .size(160.dp)
                .constrainAs(image) {
                    top.linkTo(column.top)
                    bottom.linkTo(column.top)
                    start.linkTo(column.start)
                    end.linkTo(column.end)
                }
        ) {
            if (loadingPic)
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(12.dp)
                        .fillMaxSize()
                        .shadow(
                            8.dp,
                            shape = CircleShape,
                            spotColor = MaterialTheme.colorScheme.primary
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
                        .border(4.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
                        .padding(48.dp)
                )
            else
                Image(
                    painter =
                    if (profilePic != null)
                        rememberAsyncImagePainter(model = profilePic)
                    else
                        painterResource(R.drawable.ic_avatar),
                    contentDescription = "Circular Image",
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                        .shadow(
                            8.dp,
                            shape = CircleShape,
                            spotColor = MaterialTheme.colorScheme.primary
                        )
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f))
                        .border(4.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
                        .padding(4.dp)
                )

            Icon(
                painter = painterResource(id = R.drawable.ic_add),  // عکس دلخواه
                contentDescription = "add",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 26.dp)
                    .size(40.dp)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f))
                    .clickable { onProfilePicChange() }
            )
        }

    }
}