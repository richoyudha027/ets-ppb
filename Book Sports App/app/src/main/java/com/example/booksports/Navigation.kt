package com.example.booksports

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.booksports.ui.page.*

sealed class Route(val route: String) {
    object Welcome : Route("welcome")
    object BookingList : Route("booking_list")
    object Booking : Route("booking")
    object Confirmation : Route("confirmation")
    object Home : Route("home")
    object HomeFlow : Route("home_flow")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Welcome.route
    ) {
        composable(Route.Welcome.route) {
            WelcomePage(
                onTimeout = {
                    // Navigate to Home Flow after timeout
                    navController.navigate(Route.HomeFlow.route) {
                        popUpTo(Route.Welcome.route) { inclusive = true }
                    }
                }
            )
        }

        navigation(
            startDestination = Route.Home.route,
            route = Route.HomeFlow.route
        ) {
            composable(Route.Home.route) {
                HomePage(
                    onBookingClick = {
                        navController.navigate(Route.Booking.route)
                    },
                    onBookingListClick = {
                        navController.navigate(Route.BookingList.route)
                    }
                )
            }

            composable(Route.BookingList.route) {
                BookingListPage(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }

            composable(Route.Booking.route) {
                BookingPage(
                    onHomeClick = {
                        navController.navigateUp()
                    },
                    onConfirmationClick = {
                        navController.navigate(Route.Confirmation.route)
                    }
                )
            }

            composable(Route.Confirmation.route) {
                ConfirmationPage(
                    onHomeClick = {
                        navController.navigate(Route.Home.route) {
                            popUpTo(Route.HomeFlow.route) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}