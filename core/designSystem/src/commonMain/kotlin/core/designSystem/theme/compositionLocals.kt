package core.designSystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf


internal val LocalAppColors = compositionLocalOf<AppColors> {
    error("AppColors is not provided")
}

internal val LocalThemeMode = compositionLocalOf<ThemeMode> {
    error("ThemeMode is not provided")
}

internal val LocalUserThemeMode = compositionLocalOf<UserThemeMode> {
    error("UserThemeMode is not provided")
}

internal val LocalAppTypography = compositionLocalOf<AppTypography> {
    error("UserThemeMode is not provided")
}

object AppTheme {
    val themeMode: ThemeMode
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeMode.current

    val userTheme: UserThemeMode
        @Composable
        @ReadOnlyComposable
        get() = LocalUserThemeMode.current

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val dimens = AppDimensions

    val shapes = Shapes

}
