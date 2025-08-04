package core.designSystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTheme(
  userThemeMode: UserThemeMode = UserThemeMode.Light,
  content: @Composable () -> Unit
) {
  val isSystemInDarkTheme = isSystemInDarkTheme()

  val themeMode = remember(userThemeMode, isSystemInDarkTheme) {
    when (userThemeMode) {
      UserThemeMode.System ->
        if (isSystemInDarkTheme) {
          ThemeMode.Dark
        } else {
          ThemeMode.Light
        }

      UserThemeMode.Light -> ThemeMode.Light
      UserThemeMode.Dark -> ThemeMode.Dark
      UserThemeMode.Black -> ThemeMode.Black
    }
  }

  val colors = remember(themeMode) {
    when (themeMode) {
      ThemeMode.Light -> AppColors.light
      ThemeMode.Dark -> AppColors.dark
      ThemeMode.Black -> AppColors.black
    }
  }



  CompositionLocalProvider(
    LocalThemeMode provides themeMode,
    LocalUserThemeMode provides userThemeMode,
    LocalAppColors provides colors,
    LocalAppTypography provides BanterChatFlipTypography,
  ) {
    //comment this for preview
    SyncOsTheme(AppTheme.userTheme, AppTheme.themeMode)
    MaterialTheme(colorScheme = appColorScheme) {
      CompositionLocalProvider(
        LocalRippleConfiguration provides RippleConfiguration(
          rippleAlpha = RippleAlpha(
            pressedAlpha = 0.10f,
            focusedAlpha = 0.12f,
            draggedAlpha = 0.08f,
            hoveredAlpha = 0.04f,
          ),
          color = colors.primary
        ),
        LocalTextStyle provides AppTheme.typography.body.DefaultMedium,
      ) {
        content()
      }
    }
  }
}
