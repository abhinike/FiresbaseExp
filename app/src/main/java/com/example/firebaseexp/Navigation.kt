package com.example.firebaseexp

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun nativation() {

    var navcontroller  = rememberNavController()

    NavHost(navController = navcontroller, startDestination = Screen.MainScreen.route){

        composable(route = Screen.MainScreen.route){
                Screen1(navController = navcontroller)
        }

        composable(route = Screen.MapScreen.route){
            Screen2()
        }


    }


}


@Composable
fun Screen1(navController: NavController) {
    Button(onClick = { navController.navigate(Screen.MapScreen.route)}) {
        Text(text = "hello")
    }

}

@Composable
fun Screen2() {
    Text(text = "screen2")

}