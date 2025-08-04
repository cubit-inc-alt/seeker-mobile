package core.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import core.designSystem.theme.AppTheme
import core.features.main.MainBottomNavScreen
import core.ui.components.toast.ToastHost
import core.ui.navigation.AppNavigation

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppUI(viewModel: AppUIViewModel) {
  setSingletonImageLoaderFactory { context ->
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
  }

  val state by viewModel.state.collectAsState()

  setSingletonImageLoaderFactory { context ->
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
  }

  AppTheme {
    val navigator = rememberNavController()
    Box(Modifier.fillMaxSize()) {
      ToastHost {
        NavHost(
          navController = navigator, startDestination = AppNavigation.ChatScreen
        ) {
          composable<AppNavigation.ChatScreen> {
            MainBottomNavScreen() {}
          }
        }
      }
    }
  }
}
