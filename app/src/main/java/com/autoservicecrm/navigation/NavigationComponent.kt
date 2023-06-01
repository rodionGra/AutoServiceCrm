package com.autoservicecrm.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.autoservicecrm.car.ui.CarManagementScreen
import com.autoservicecrm.home.ui.HomeScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, hiltViewModel())
        }
        composable(route = Screen.CarManagement.route) {
            CarManagementScreen(hiltViewModel())
        }
    }
}
