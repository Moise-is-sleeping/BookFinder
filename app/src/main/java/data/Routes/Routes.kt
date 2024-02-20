package data.Routes

sealed class Routes(val route :String) {
    object LoginScreen : Routes("LoginScreen")
    object HomeScreen :Routes("HomeScreen")
    object RegisterScreen:Routes("RegisterScreen")
}