package com.autoservicecrm.navigation

sealed class Screen(
    open val route: String
) {
    object HomeScreen : Screen("homeScreen")
    object CarManagement : Screen("CarManagement")
    object MasterManagement : Screen("MasterManagement")
    object OrderManagement : Screen("OrderManagement")

    @Deprecated("all args should be not null")
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}