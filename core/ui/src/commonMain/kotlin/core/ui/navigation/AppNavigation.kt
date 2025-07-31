package core.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavigation {

  @Serializable
  data object ChatScreen : AppNavigation
  @Serializable
  data object Forum : AppNavigation
  @Serializable
  data object CoinFlip : AppNavigation

}
