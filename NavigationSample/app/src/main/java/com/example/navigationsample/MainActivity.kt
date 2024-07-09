package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NavigationSampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}


/*
Key Changes:
Gson Dependency:

Add the Gson library dependency to your project.

=> Serialization and Deserialization:
* Convert the MyCollage object to a JSON string using Gson().toJson() and encode it with Uri.encode() before passing it in the navigation route.
* Decode the JSON string and convert it back to a MyCollage object using Gson().fromJson() in SecondScreen.

=> Passing and Retrieving Data:
* Pass the MyCollage object from FirstScreen to SecondScreen by encoding it as a JSON string.
* Retrieve and decode the JSON string back to a MyCollage object in SecondScreen.
By following these steps, you can pass complex data classes between screens in a Jetpack Compose navigation setup.
 */

/*
NavHost(navController = navController, startDestination = "firstScreen") {
        composable("firstScreen") {
            FirstScreen(
                // null name handled
                //navigationToSecondScreen = { name -> navController.navigate("secondScreen/${name.ifEmpty{"Guest"}}") },
                navigationToSecondScreen = { name -> navController.navigate("secondScreen?name=$name") },
                navigateToThirdScreen = { navController.navigate("thirdScreen") }
            )
        }
        composable(
            //route = "secondScreen/{name}",
            //arguments = listOf(navArgument("name"){type = NavType.StringType})

            //This is another way to handle null 😄
            route = "secondScreen?name={name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                nullable = true
                defaultValue = ""
            })
        ) {
            // null value handled
            //val name = it.arguments?.getString("name") ?: "Guest"
            val name = it.arguments?.getString("name") ?: ""
            SecondScreen(
                name = name,
                navigateToThirdScreen = { navController.navigate("thirdScreen") },
                navigateToFirstScreen = { navController.navigate("firstScreen") }
            )
        }
        composable("thirdScreen") {
            ThirdScreen(
                navigateToFirstScreen = { navController.navigate("firstScreen") },
                navigationToSecondScreen = { navController.navigate("secondScreen") }
            )
        }
    }
 */

