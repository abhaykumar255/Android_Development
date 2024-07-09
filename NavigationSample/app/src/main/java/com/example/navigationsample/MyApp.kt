package com.example.navigationsample

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun MyApp() {
    val navController = rememberNavController()

    /*
    => Why we are passing it like Json
    Passing complex data types directly in navigation arguments is
    not supported by default in Jetpack Compose Navigation. We use
    JSON serialization as a workaround because strings can be safely
    passed as arguments and reconstructed back into the original data
    type on the receiving side. This is a common technique to handle
    the limitations of navigation arguments.
     */

    // Setting the navHost
    NavHost(navController = navController, startDestination = "firstScreen") {
        composable("firstScreen") {
            FirstScreen(
                navigationToSecondScreen = { collage ->
                    val collageJson = Uri.encode(Gson().toJson(collage))
                    Log.d("collageJsonResponse",collageJson)
                    navController.navigate("secondScreen/$collageJson")
                },
                navigateToThirdScreen = { navController.navigate("thirdScreen") }
            )
        }
        composable(
            "secondScreen/{collageJson}",
            arguments = listOf(navArgument("collageJson") { type = NavType.StringType })
        ) {
            val collageJson = it.arguments?.getString("collageJson")
            val collage = Gson().fromJson(collageJson, MyCollage::class.java)
            SecondScreen(collage = collage,
                navigateToFirstScreen = { navController.navigate("firstScreen") },
                navigateToThirdScreen = { navController.navigate("thirdScreen") }
            )
        }
        composable("thirdScreen") {
            ThirdScreen(
                navigateToFirstScreen = { navController.navigate("firstScreen") },
                navigationToSecondScreen = { navController.navigate("secondScreen") }
            )
        }
    }

}