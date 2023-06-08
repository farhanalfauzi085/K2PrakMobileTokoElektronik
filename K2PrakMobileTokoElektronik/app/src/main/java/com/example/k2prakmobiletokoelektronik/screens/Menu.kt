package com.example.k2prakmobiletokoelektronik.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.K2PrakMobileTokoElektronik.R
import com.example.k2prakmobiletokoelektronik.model.Komputer

enum class Menu (
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
){
    HOME(R.string.home, Icons.Default.Home, "home"),
    KOMPUTER(R.string.komputer, Icons.Default.Person, "komputer"),
    PERIFERAL(R.string.periferal, Icons.Default.AccountBox, "periferal"),
    SMARTPHONE(R.string.smartphone, Icons.Default.Email, "smartphone");

    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                R.string.komputer -> KOMPUTER
                R.string.periferal -> PERIFERAL
                else -> SMARTPHONE
            }
        }
    }
}