package fpoly.longlt.assignment.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme
import fpoly.longlt.assignment.viewmodel.ProductViewModel

class NavNavigation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                ScreenNavigation()
            }
        }
    }
}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val productViewModel = ProductViewModel()
    NavHost(navController = navController, startDestination = Screen.HOMESCREEN.route) {
        composable(Screen.HOMESCREEN.route) {
            PreviewList(navController)
        }
        composable(
            "${Screen.DETAILSCREEN.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let {
                id -> Detail(navController, id, productViewModel)
            }
        }
        composable(Screen.ADMINSCREEN.route) {
            ScreenAdmin(navController, productViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    AssignmentTheme {
    }
}