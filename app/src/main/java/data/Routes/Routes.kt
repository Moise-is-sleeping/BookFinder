package data.Routes



sealed class Routes(val route :String) {
    object LoginScreen : Routes("LoginScreen")
    object HomeScreen :Routes("HomeScreen")
    object RegisterScreen:Routes("RegisterScreen")
    object SearchScreen:Routes("SearchScreen")
    object BookDescriptionScreen:Routes("BookDescriptionScreen")
    object SavedScreen:Routes("SavedScreen")
}