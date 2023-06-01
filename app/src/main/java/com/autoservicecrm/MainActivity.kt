package com.autoservicecrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.autoservicecrm.navigation.NavigationComponent
import com.autoservicecrm.shared.ui.theme.AutoServiceCrmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutoServiceCrmTheme {
                val navController = rememberNavController()

                NavigationComponent(navController = navController)
            }
        }
    }
}
