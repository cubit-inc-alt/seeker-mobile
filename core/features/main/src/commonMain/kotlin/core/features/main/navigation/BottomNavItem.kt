package core.features.main.navigation

import core.resources.generated.resources.Res
import core.resources.generated.resources.coin_flip
import core.resources.generated.resources.forum
import core.resources.generated.resources.ic_coin_flip
import core.resources.generated.resources.ic_forum
import core.ui.navigation.AppNavigation
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


sealed class BottomNavItem(
  val route: AppNavigation,
  val label: StringResource,
  val icon: DrawableResource
) {
  data object Forum :
    BottomNavItem(
      AppNavigation.Forum,
      Res.string.forum,
      Res.drawable.ic_forum
    )

  data object CoinFlip : BottomNavItem(
    AppNavigation.CoinFlip,
    Res.string.coin_flip,
    Res.drawable.ic_coin_flip
  )

}

val bottomNavItems = listOf(
  BottomNavItem.Forum,
  BottomNavItem.CoinFlip,
)

