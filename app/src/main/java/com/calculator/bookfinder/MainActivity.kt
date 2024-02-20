package com.calculator.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calculator.bookfinder.ui.theme.BookFinderTheme
import data.Routes.Routes
import ui.Screens.HomeScreen
import ui.Screens.HomeScreen
import ui.Screens.LoginScreen
import ui.Screens.RegisterScreen
import ui.ViewModel.BookViewModel
import ui.Screens.Screen
import ui.ViewModel.LoginViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bookViewModel = BookViewModel()
                    val loginViewModel = LoginViewModel()

                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = Routes.LoginScreen.route ){
                        composable(Routes.LoginScreen.route){
                            LoginScreen(loginViewModel,navController)
                        }
                        composable(Routes.HomeScreen.route){
                            HomeScreen(BookViewModel())
                        }
                        composable(Routes.RegisterScreen.route){
                            RegisterScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookFinderTheme {
        Greeting("Android")
    }
}