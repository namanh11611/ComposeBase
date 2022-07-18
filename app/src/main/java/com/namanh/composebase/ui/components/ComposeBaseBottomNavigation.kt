package com.namanh.composebase.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.namanh.composebase.data.model.Tab

@Composable
fun ComposeBaseBottomNavigation(navController: NavController, modifier: Modifier) {
    val tabs = Tab.values().toList()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        tabs.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = it.icon, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = it.titleId))
                },
                selected = currentRoute == it.route,
                onClick = { navController.navigate(it.route) }
            )
        }
    }
}