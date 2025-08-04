package core.designSystem.theme

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

actual object AppDimensions {
    actual val zero = 0.dp
    actual val half = 2.dp
    actual val size_4 = 4.dp
    actual val size_6 = 6.dp
    actual val size_8 = 8.dp
    actual val size_10 = 10.dp
    actual val size_12 = 12.dp
    actual val size_14 = 14.dp
    actual val size_16 = 16.dp
    actual val size_18 = 18.dp
    actual val size_20 = 20.dp
    actual val size_22 = 22.dp
    actual val size_24 = 24.dp
    actual val size_26 = 26.dp
    actual val size_28 = 28.dp
    actual val size_30 = 30.dp
    actual val size_32 = 32.dp
    actual val size_34 = 34.dp
    actual val size_36 = 36.dp
    actual val size_38 = 38.dp
    actual val size_40 = 40.dp
    actual val size_42 = 42.dp
    actual val size_44 = 44.dp
    actual val size_46 = 46.dp
    actual val size_48 = 48.dp
    actual val size_50 = 50.dp
    actual val size_52 = 52.dp
    actual val size_54 = 54.dp
    actual val size_56 = 56.dp
    actual val size_58 = 58.dp
    actual val size_60 = 60.dp
    actual val size_62 = 62.dp
}


actual object AppTextSize {
    actual val size_8 = 8.sp
    actual val size_10 = 10.sp
    actual val size_12 = 12.sp
    actual val size_14 = 14.sp
    actual val size_16 = 16.sp
    actual val size_18 = 18.sp
    actual val size_20 = 20.sp
    actual val size_22 = 22.sp
    actual val size_24 = 24.sp
    actual val size_26 = 26.sp
    actual val size_28 = 28.sp
    actual val size_30 = 30.sp
    actual val size_32 = 32.sp
    actual val size_34 = 34.sp
    actual val size_36 = 36.sp
    actual val size_38 = 38.sp
    actual val size_40 = 40.sp
    actual val size_42 = 42.sp
    actual val size_44 = 44.sp
    actual val size_46 = 46.sp
    actual val size_48 = 48.sp
    actual val size_50 = 50.sp
    actual val size_52 = 52.sp
    actual val size_54 = 54.sp
    actual val size_56 = 56.sp
    actual val size_58 = 58.sp
    actual val size_60 = 60.sp
    actual val size_62 = 62.sp
    actual val size_64 = 64.sp
    actual val size_66 = 66.sp
}

private val density = Resources.getSystem().displayMetrics.density
private const val baseWidth = 360f  // dp width for reference device (1080px / 3)

fun getScreenWidthDp(): Float = Resources.getSystem().displayMetrics.widthPixels / density

private val scaleFactor = getScreenWidthDp() / baseWidth

actual fun scaleDp(value: Float): Dp = (value * scaleFactor).dp
actual fun scaleSp(value: Float): TextUnit = (value * scaleFactor).sp
