package com.chaykalak.mycookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.chaykalak.mycookbook.navigation.CookbookNavHost
import com.chaykalak.mycookbook.ui.theme.MyCookbookTheme
import com.chaykalak.mycookbook.viewmodel.RecipesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCookbookTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCookbookApp()
                }
            }
        }
    }
}

@Composable
fun MyCookbookApp() {
    val navController = rememberNavController()
    val viewModel: RecipesViewModel = viewModel()
    CookbookNavHost(
        navController = navController,
        viewModel = viewModel
    )
}