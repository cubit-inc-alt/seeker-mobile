package core.designSystem.helper

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import core.designSystem.theme.AppColors
import core.designSystem.theme.AppTextSize.size_16

class PlaceholderTransformation(private val placeholder: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return placeholderFilter(text, placeholder)
    }
}

fun placeholderFilter(text: AnnotatedString, placeholder: String): TransformedText {

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return 0
        }

        override fun transformedToOriginal(offset: Int): Int {
            return 0
        }
    }

    return TransformedText(buildAnnotatedString {
        withStyle(
            SpanStyle(
                AppColors.black30,
                fontSize = size_16,
                fontWeight = FontWeight.Normal,
            )
        ) {
            append(placeholder)
        }
    }, numberOffsetTranslator)
}
