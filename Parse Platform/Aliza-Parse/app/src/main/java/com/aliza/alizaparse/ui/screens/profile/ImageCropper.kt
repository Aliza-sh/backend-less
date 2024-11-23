package com.aliza.alizaparse.ui.screens.profile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.aliza.alizaparse.ui.theme.Green
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.UUID

@Composable
fun ImageCropper(
    onImageCropped: (Uri) -> Unit
): ManagedActivityResultLauncher<String, Uri?> {

    val context = LocalContext.current

    val cropLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val outputUri = result.data?.let { UCrop.getOutput(it) }
            if (result.resultCode == RESULT_OK && outputUri != null) {
                onImageCropped(outputUri) // برگرداندن عکس کراپ‌شده
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                startCrop(uri = uri, context = context, cropLauncher = cropLauncher)
            }
        }
    )

    return galleryLauncher
}

fun startCrop(
    uri: Uri,
    context: Context,
    cropLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {

    val name = UUID.randomUUID().toString() + ".jpg"
    val destinationUri = Uri.fromFile(
        File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            name
        )
    )

    val options = UCrop.Options().apply {
        setCircleDimmedLayer(true)


        setToolbarColor(Green.toArgb())
        setToolbarWidgetColor(Color.WHITE)

        setActiveControlsWidgetColor(Green.toArgb())
        setToolbarTitle("")

        setDimmedLayerColor(Color.parseColor("#AA000000"))
        setStatusBarColor(Green.toArgb())

        setHideBottomControls(true)
        setShowCropGrid(false)

        setShowCropFrame(false)
    }

    val uCrop = UCrop.of(uri, destinationUri)
        .withAspectRatio(1f, 1f)
        .withMaxResultSize(512, 512)
        .withOptions(options)

    cropLauncher.launch(uCrop.getIntent(context))
}