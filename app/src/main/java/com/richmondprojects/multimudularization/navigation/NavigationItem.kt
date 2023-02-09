package com.richmondprojects.multimudularization.navigation

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem("Home")
    object DetailScreen : NavigationItem("Details/{characterUrl}")
}
