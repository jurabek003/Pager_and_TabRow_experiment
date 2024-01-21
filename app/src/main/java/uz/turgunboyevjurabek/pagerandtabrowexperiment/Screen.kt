package uz.turgunboyevjurabek.pagerandtabrowexperiment

sealed class Screen(val title:String)
object HomeScreen : Screen("Home")
object CarScreen : Screen("Search")
object NaturalScreen : Screen("Profile")
object MixScreen : Screen("Settings")
