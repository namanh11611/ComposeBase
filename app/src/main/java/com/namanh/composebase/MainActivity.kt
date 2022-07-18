package com.namanh.composebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.namanh.composebase.data.model.Tab
import com.namanh.composebase.ui.components.ComposeBaseBottomNavigation
import com.namanh.composebase.ui.home.HomeScreen
import com.namanh.composebase.ui.theme.ComposeBaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBaseApp()
        }
    }
}

@Composable
fun ComposeBaseApp() {
    ComposeBaseTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                ComposeBaseBottomNavigation(navController, Modifier)
            }
        ) {
            ComposeBaseNavHost(navController, Modifier)
        }
    }
}

@Composable
fun ComposeBaseNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Tab.Home.route,
        modifier = modifier
    ) {
        composable(Tab.Home.route) {
            HomeScreen(modifier = modifier)
        }
        composable(Tab.Feed.route) {
            Greeting(name = Tab.Feed.name)
        }
        composable(Tab.Profile.route) {
            Greeting(name = Tab.Profile.name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Hello $name!",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Greeting(name = "Henry")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBaseApp()
}