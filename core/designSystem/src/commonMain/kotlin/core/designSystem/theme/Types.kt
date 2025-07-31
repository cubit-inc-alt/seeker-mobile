package core.designSystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import core.designSystem.theme.AppTextSize.size_32
import core.designSystem.theme.AppTextSize.size_36
import core.designSystem.theme.AppTextSize.size_40

private val typography: Typography
  @Composable
  get() = MaterialTheme.typography

@Composable
fun rememberTextStyle(
  baseStyle: TextStyle,
  fontWeight: FontWeight,
): TextStyle {
  return remember(baseStyle, fontWeight) {
    baseStyle.copy(
      fontWeight = fontWeight,
      fontFamily = FontFamily.SansSerif
    )
  }
}


@Suppress("PropertyName")
interface AppTypography {
  val display: Display
  val heading: Heading
  val body: Body
  val label: Label

  interface Display {
    @get:Composable
    val SmallRegular: TextStyle

    @get:Composable
    val DefaultRegular: TextStyle

    @get:Composable
    val LargeRegular: TextStyle

    @get:Composable
    val SmallMedium: TextStyle

    @get:Composable
    val DefaultMedium: TextStyle

    @get:Composable
    val LargeMedium: TextStyle

    @get:Composable
    val SmallSemiBold: TextStyle

    @get:Composable
    val DefaultSemiBold: TextStyle

    @get:Composable
    val LargeSemiBold: TextStyle

    @get:Composable
    val SmallBold: TextStyle

    @get:Composable
    val DefaultBold: TextStyle

    @get:Composable
    val LargeBold: TextStyle
  }

  interface Heading {
    @get:Composable
    val SmallRegular: TextStyle

    @get:Composable
    val DefaultRegular: TextStyle

    @get:Composable
    val LargeRegular: TextStyle

    @get:Composable
    val SmallMedium: TextStyle

    @get:Composable
    val DefaultMedium: TextStyle

    @get:Composable
    val LargeMedium: TextStyle

    @get:Composable
    val SmallSemiBold: TextStyle

    @get:Composable
    val DefaultSemiBold: TextStyle

    @get:Composable
    val LargeSemiBold: TextStyle

    @get:Composable
    val SmallBold: TextStyle

    @get:Composable
    val DefaultBold: TextStyle

    @get:Composable
    val LargeBold: TextStyle
  }

  interface Body {
    @get:Composable
    val SmallRegular: TextStyle

    @get:Composable
    val DefaultRegular: TextStyle

    @get:Composable
    val LargeRegular: TextStyle

    @get:Composable
    val SmallMedium: TextStyle

    @get:Composable
    val DefaultMedium: TextStyle

    @get:Composable
    val LargeMedium: TextStyle

    @get:Composable
    val SmallSemiBold: TextStyle

    @get:Composable
    val DefaultSemiBold: TextStyle

    @get:Composable
    val LargeSemiBold: TextStyle

    @get:Composable
    val SmallBold: TextStyle

    @get:Composable
    val DefaultBold: TextStyle

    @get:Composable
    val LargeBold: TextStyle
  }

  interface Label {
    @get:Composable
    val SmallRegular: TextStyle

    @get:Composable
    val DefaultRegular: TextStyle

    @get:Composable
    val LargeRegular: TextStyle

    @get:Composable
    val SmallMedium: TextStyle

    @get:Composable
    val DefaultMedium: TextStyle

    @get:Composable
    val LargeMedium: TextStyle

    @get:Composable
    val SmallSemiBold: TextStyle

    @get:Composable
    val DefaultSemiBold: TextStyle

    @get:Composable
    val LargeSemiBold: TextStyle

    @get:Composable
    val SmallBold: TextStyle

    @get:Composable
    val DefaultBold: TextStyle

    @get:Composable
    val LargeBold: TextStyle
  }
}

internal object BanterChatFlipTypography : AppTypography {

  override val display = object : AppTypography.Display {
    override val SmallRegular
      @Composable get() = rememberTextStyle(
        typography.displaySmall.copy(fontSize = size_32),
        FontWeight.Normal
      )
    override val DefaultRegular
      @Composable get() = rememberTextStyle(
        typography.displayMedium.copy(fontSize = size_36),
        FontWeight.Normal
      )
    override val LargeRegular
      @Composable get() = rememberTextStyle(
        typography.displayLarge.copy(fontSize = size_40),
        FontWeight.Normal
      )

    override val SmallMedium
      @Composable get() = rememberTextStyle(
        typography.displaySmall.copy(fontSize = size_32),
        FontWeight.Medium
      )
    override val DefaultMedium
      @Composable get() = rememberTextStyle(
        typography.displayMedium.copy(fontSize = size_36),
        FontWeight.Medium
      )
    override val LargeMedium
      @Composable get() = rememberTextStyle(
        typography.displayLarge.copy(fontSize = size_40),
        FontWeight.Medium
      )

    override val SmallSemiBold
      @Composable get() = rememberTextStyle(
        typography.displaySmall.copy(fontSize = size_32),
        FontWeight.SemiBold
      )
    override val DefaultSemiBold
      @Composable get() = rememberTextStyle(
        typography.displayMedium.copy(fontSize = size_36),
        FontWeight.SemiBold
      )
    override val LargeSemiBold
      @Composable get() = rememberTextStyle(
        typography.displayLarge.copy(fontSize = size_40),
        FontWeight.SemiBold
      )

    override val SmallBold
      @Composable get() = rememberTextStyle(
        typography.displaySmall.copy(fontSize = size_32),
        FontWeight.Bold
      )
    override val DefaultBold
      @Composable get() = rememberTextStyle(
        typography.displayMedium.copy(fontSize = size_36),
        FontWeight.Bold
      )
    override val LargeBold
      @Composable get() = rememberTextStyle(
        typography.displayLarge.copy(fontSize = size_40),
        FontWeight.Bold
      )
  }

  override val heading = object : AppTypography.Heading {
    override val SmallRegular
      @Composable get() = rememberTextStyle(typography.titleSmall, FontWeight.Normal)
    override val DefaultRegular
      @Composable get() = rememberTextStyle(typography.titleMedium, FontWeight.Normal)
    override val LargeRegular
      @Composable get() = rememberTextStyle(typography.titleLarge, FontWeight.Normal)

    override val SmallMedium
      @Composable get() = rememberTextStyle(typography.titleSmall, FontWeight.Medium)
    override val DefaultMedium
      @Composable get() = rememberTextStyle(typography.titleMedium, FontWeight.Medium)
    override val LargeMedium
      @Composable get() = rememberTextStyle(typography.titleLarge, FontWeight.Medium)

    override val SmallSemiBold
      @Composable get() = rememberTextStyle(typography.titleSmall, FontWeight.SemiBold)
    override val DefaultSemiBold
      @Composable get() = rememberTextStyle(typography.titleMedium, FontWeight.SemiBold)
    override val LargeSemiBold
      @Composable get() = rememberTextStyle(typography.titleLarge, FontWeight.SemiBold)

    override val SmallBold
      @Composable get() = rememberTextStyle(typography.titleSmall, FontWeight.Bold)
    override val DefaultBold
      @Composable get() = rememberTextStyle(typography.titleMedium, FontWeight.Bold)
    override val LargeBold
      @Composable get() = rememberTextStyle(typography.titleLarge, FontWeight.Bold)
  }

  override val body = object : AppTypography.Body {
    override val SmallRegular
      @Composable get() = rememberTextStyle(typography.bodySmall, FontWeight.Normal)
    override val DefaultRegular
      @Composable get() = rememberTextStyle(typography.bodyMedium, FontWeight.Normal)
    override val LargeRegular
      @Composable get() = rememberTextStyle(typography.bodyLarge, FontWeight.Normal)

    override val SmallMedium
      @Composable get() = rememberTextStyle(typography.bodySmall, FontWeight.Medium)
    override val DefaultMedium
      @Composable get() = rememberTextStyle(typography.bodyMedium, FontWeight.Medium)
    override val LargeMedium
      @Composable get() = rememberTextStyle(typography.bodyLarge, FontWeight.Medium)

    override val SmallSemiBold
      @Composable get() = rememberTextStyle(typography.bodySmall, FontWeight.SemiBold)
    override val DefaultSemiBold
      @Composable get() = rememberTextStyle(typography.bodyMedium, FontWeight.SemiBold)
    override val LargeSemiBold
      @Composable get() = rememberTextStyle(typography.bodyLarge, FontWeight.SemiBold)

    override val SmallBold
      @Composable get() = rememberTextStyle(typography.bodySmall, FontWeight.Bold)
    override val DefaultBold
      @Composable get() = rememberTextStyle(typography.bodyMedium, FontWeight.Bold)
    override val LargeBold
      @Composable get() = rememberTextStyle(typography.bodyLarge, FontWeight.Bold)
  }

  override val label = object : AppTypography.Label {
    override val SmallRegular
      @Composable get() = rememberTextStyle(typography.labelSmall, FontWeight.Normal)
    override val DefaultRegular
      @Composable get() = rememberTextStyle(typography.labelMedium, FontWeight.Normal)
    override val LargeRegular
      @Composable get() = rememberTextStyle(typography.labelLarge, FontWeight.Normal)

    override val SmallMedium
      @Composable get() = rememberTextStyle(typography.labelSmall, FontWeight.Medium)
    override val DefaultMedium
      @Composable get() = rememberTextStyle(typography.labelMedium, FontWeight.Medium)
    override val LargeMedium
      @Composable get() = rememberTextStyle(typography.labelLarge, FontWeight.Medium)

    override val SmallSemiBold
      @Composable get() = rememberTextStyle(typography.labelSmall, FontWeight.SemiBold)
    override val DefaultSemiBold
      @Composable get() = rememberTextStyle(typography.labelMedium, FontWeight.SemiBold)
    override val LargeSemiBold
      @Composable get() = rememberTextStyle(typography.labelLarge, FontWeight.SemiBold)

    override val SmallBold
      @Composable get() = rememberTextStyle(typography.labelSmall, FontWeight.Bold)
    override val DefaultBold
      @Composable get() = rememberTextStyle(typography.labelMedium, FontWeight.Bold)
    override val LargeBold
      @Composable get() = rememberTextStyle(typography.labelLarge, FontWeight.Bold)
  }
}
