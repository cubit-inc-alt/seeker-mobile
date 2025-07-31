package core.features.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import core.designSystem.theme.AppTheme.typography
import core.features.chat.ChatScreen
import core.features.main.navigation.BottomNavItem
import core.features.main.navigation.bottomNavItems
import core.ui.navigation.AppNavigation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MainBottomNavScreen(
    navigateToNext: (AppNavigation) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Column {
                HorizontalDivider()

                NavigationBar(containerColor = Color.White) {
                    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
                    val currentRoute = currentBackStackEntry?.destination?.route

                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route.toString(),
                            onClick = {
                                if (currentRoute != item.route.toString()) {
                                    navController.navigate(item.route.toString()) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = stringResource(item.label)
                                )
                            },
                            label = {
                                Text(
                                    stringResource(item.label),
                                    style = typography.label.SmallSemiBold
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent, selectedIconColor = Color.Black,
                            ),
                        )
                    }
                }
            }
        }, containerColor = Color.White
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Forum.route.toString(),
            modifier = Modifier.fillMaxSize().padding(bottom = padding.calculateBottomPadding())
                .background(Color.White),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            composable(BottomNavItem.Forum.route.toString()) {
                ChatScreen(koinInject()) {
                    navigateToNext(it)
                }


            }
            composable(BottomNavItem.CoinFlip.route.toString()) {

            }
        }
    }
}
