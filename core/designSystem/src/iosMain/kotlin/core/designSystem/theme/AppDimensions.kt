package core.designSystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

// Scaling factor = 1.08

actual object AppDimensions {
    actual val zero = 0.dp
    actual val half = 2.16.dp
    actual val size_4 = 4.32.dp
    actual val size_6 = 6.48.dp
    actual val size_8 = 8.64.dp
    actual val size_10 = 10.8.dp
    actual val size_12 = 12.96.dp
    actual val size_14 = 15.12.dp
    actual val size_16 = 17.28.dp
    actual val size_18 = 19.44.dp
    actual val size_20 = 21.6.dp
    actual val size_22 = 23.76.dp
    actual val size_24 = 25.92.dp
    actual val size_26 = 28.08.dp
    actual val size_28 = 30.24.dp
    actual val size_30 = 32.4.dp
    actual val size_32 = 34.56.dp
    actual val size_34 = 36.72.dp
    actual val size_36 = 38.88.dp
    actual val size_38 = 41.04.dp
    actual val size_40 = 43.2.dp
    actual val size_42 = 45.36.dp
    actual val size_44 = 47.52.dp
    actual val size_46 = 49.68.dp
    actual val size_48 = 51.84.dp
    actual val size_50 = 54.0.dp
    actual val size_52 = 56.16.dp
    actual val size_54 = 58.32.dp
    actual val size_56 = 60.48.dp
    actual val size_58 = 62.64.dp
    actual val size_60 = 64.8.dp
    actual val size_62 = 66.96.dp
}

actual object AppTextSize {
    actual val size_8 = 10.sp
    actual val size_10 = 12.sp
    actual val size_12 = 14.sp
    actual val size_14 = 17.sp
    actual val size_16 = 19.sp
    actual val size_18 = 22.sp
    actual val size_20 = 24.sp
    actual val size_22 = 26.sp
    actual val size_24 = 29.sp
    actual val size_26 = 31.sp
    actual val size_28 = 34.sp
    actual val size_30 = 36.sp
    actual val size_32 = 38.sp
    actual val size_34 = 41.sp
    actual val size_36 = 43.sp
    actual val size_38 = 46.sp
    actual val size_40 = 48.sp
    actual val size_42 = 50.sp
    actual val size_44 = 53.sp
    actual val size_46 = 55.sp
    actual val size_48 = 58.sp
    actual val size_50 = 60.sp
    actual val size_52 = 62.sp
    actual val size_54 = 65.sp
    actual val size_56 = 67.sp
    actual val size_58 = 70.sp
    actual val size_60 = 72.sp
    actual val size_62 = 74.sp
    actual val size_64 = 77.sp
    actual val size_66 = 79.sp

}

@OptIn(ExperimentalForeignApi::class)
private val screenWidthPoints: Float =
    UIScreen.mainScreen.bounds.useContents { size.width.toFloat() }
private const val baseWidth = 360f // base dp for scaling

private val scaleFactor = screenWidthPoints / baseWidth

actual fun scaleDp(value: Float): Dp = (value * scaleFactor).dp
actual fun scaleSp(value: Float): TextUnit = (value * scaleFactor).sp
