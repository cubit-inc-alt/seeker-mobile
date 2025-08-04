package core.designSystem.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

// DEFAULT_SCALE_FACTOR = 1.08f
// DEFAULT_TEXT_SIZE_SCALE_FACTOR = 1.2f

expect object AppDimensions {
    val zero: Dp
    val half: Dp
    val size_4: Dp
    val size_6: Dp
    val size_8: Dp
    val size_10: Dp
    val size_12: Dp
    val size_14: Dp
    val size_16: Dp
    val size_18: Dp
    val size_20: Dp
    val size_22: Dp
    val size_24: Dp
    val size_26: Dp
    val size_28: Dp
    val size_30: Dp
    val size_32: Dp
    val size_34: Dp
    val size_36: Dp
    val size_38: Dp
    val size_40: Dp
    val size_42: Dp
    val size_44: Dp
    val size_46: Dp
    val size_48: Dp
    val size_50: Dp
    val size_52: Dp
    val size_54: Dp
    val size_56: Dp
    val size_58: Dp
    val size_60: Dp
    val size_62: Dp
}

expect fun scaleDp(value: Float): Dp
expect fun scaleSp(value: Float): TextUnit

expect object AppTextSize {
    val size_8: TextUnit
    val size_10: TextUnit
    val size_12: TextUnit
    val size_14: TextUnit
    val size_16: TextUnit
    val size_18: TextUnit
    val size_20: TextUnit
    val size_22: TextUnit
    val size_24: TextUnit
    val size_26: TextUnit
    val size_28: TextUnit
    val size_30: TextUnit
    val size_32: TextUnit
    val size_34: TextUnit
    val size_36: TextUnit
    val size_38: TextUnit
    val size_40: TextUnit
    val size_42: TextUnit
    val size_44: TextUnit
    val size_46: TextUnit
    val size_48: TextUnit
    val size_50: TextUnit
    val size_52: TextUnit
    val size_54: TextUnit
    val size_56: TextUnit
    val size_58: TextUnit
    val size_60: TextUnit
    val size_62: TextUnit
    val size_64: TextUnit
    val size_66: TextUnit
}
