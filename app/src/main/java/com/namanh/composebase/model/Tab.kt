package com.namanh.composebase.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Feed
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.namanh.composebase.R

object Route {
    const val HOME = "home"
    const val FEED = "feed"
    const val PROFILE = "profile"
}

enum class Tab(val route: String, val icon: ImageVector, val titleId: Int) {
    Home(Route.HOME, Icons.Default.Home, R.string.tab_home),
    Feed(Route.FEED, Icons.Default.Feed, R.string.tab_feed),
    Profile(Route.PROFILE, Icons.Default.Person, R.string.tab_profile),
}