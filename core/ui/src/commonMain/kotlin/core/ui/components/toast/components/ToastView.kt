package core.ui.components.toast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import core.designSystem.theme.AppDimensions.size_12
import core.designSystem.theme.AppTheme.colors
import core.designSystem.theme.AppTheme.dimens
import core.designSystem.theme.AppTheme.typography
import core.designSystem.theme.Shapes
import core.resources.getMessage
import core.resources.res
import core.ui.components.toast.Toast
import core.ui.components.toast.typeColor

@Composable
internal fun ToastView(toast: Toast, onToastHide: (Toast) -> Unit) {
    LaunchedEffect(toast) {
        delay(toast.duration)
        onToastHide(toast)
    }

    Row(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.ime.union(WindowInsets.navigationBars))
            .shadow(
                elevation = size_12,
                shape = Shapes.medium,
                ambientColor = Color.Transparent,
            )
            .background(colors.surface, Shapes.medium)
            .heightIn(min = dimens.size_44)
            .padding(horizontal = dimens.size_4, vertical = dimens.size_8),
        horizontalArrangement = Arrangement.spacedBy(dimens.size_8),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        toast.icon?.also { icon ->
            Icon(icon, toast.message, tint = toast.typeColor)
        }

        toast.textIcon?.also {
            Text(it, style = typography.body.DefaultMedium)
        }

        Text(
            toast.message?.getMessage ?: toast.messageRes?.res ?: "",
            style = typography.body.DefaultMedium,
            color = toast.typeColor,
        )
    }
}
