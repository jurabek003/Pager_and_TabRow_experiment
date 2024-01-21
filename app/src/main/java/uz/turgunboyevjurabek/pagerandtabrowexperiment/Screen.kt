package uz.turgunboyevjurabek.pagerandtabrowexperiment

sealed class Screen(val title:String)
object HomeScreen : Screen("Home")
object SearchScreen : Screen("Search")
object ProfileScreen : Screen("Profile")
object SettingsScreen : Screen("Settings")
