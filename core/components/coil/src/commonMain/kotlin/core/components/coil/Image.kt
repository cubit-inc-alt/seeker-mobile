package core.components.coil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import core.components.coil.generated.resources.Res
import core.components.coil.generated.resources.img_person_placeholder
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import core.designSystem.theme.AppTheme


enum class ImageType(val defaultImageRes: DrawableResource) {
    OTHER(Res.drawable.img_person_placeholder)
}

@Composable
fun LazyImage(
  url: String?,
  modifier: Modifier = Modifier,
  initials: String? = null,
  contentDescription: String? = null,
  type: ImageType = ImageType.OTHER,
  error: DrawableResource = type.defaultImageRes,
  alignment: Alignment = Alignment.Center,
  contentScale: ContentScale = ContentScale.Fit,
  alpha: Float = DefaultAlpha,
  background: Color = AppTheme.colors.onPrimaryContainer,
  textColor: Color = Color.White
) {

    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(url.isNullOrBlank()) }

    Box(
        modifier = modifier
            .background(AppTheme.colors.onBackground.copy(alpha = 0.03f))
    ) {
        if (hasError) {
            isLoading = false
            Box(
                modifier = modifier
                    .background(background),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials?.uppercase() ?: "",
                    style = AppTheme.typography.body.DefaultMedium,
                    color = textColor
                )
            }
        } else {
            AsyncImage(
                modifier = modifier,
                model = url,
                contentDescription = contentDescription,
                contentScale = contentScale,
                alignment = alignment,
                alpha = alpha,
                onError = {
                    if (!initials.isNullOrBlank()) {
                        hasError = true
                    }
                    isLoading = false
                },
                onLoading = {
                    isLoading = true
                },
                onSuccess = {
                    isLoading = false
                }, error = painterResource(error)
            )
        }
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
                    .sizeIn(
                        minWidth = 12.dp,
                        maxWidth = 40.dp,
                        minHeight = 12.dp,
                        maxHeight = 40.dp,
                    ),
                color = AppTheme.colors.onBackground.copy(0.15f),
                strokeWidth = 3.dp,
            )
        }
    }
}
