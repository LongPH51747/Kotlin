package fpoly.longlt.lab.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import fpoly.longlt.lab.lab6.MovieScreen
import fpoly.longlt.lab.lab7.ui.theme.Lab1Theme

class Bai1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                val mainViewModel: MovieViewModel = viewModel()
                val moviesState =
                    mainViewModel.movies.observeAsState(initial = emptyList())
            }
        }
    }
}

@Composable
fun Greeting12(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview13() {
    Lab1Theme {
        Greeting12("Android")
    }
}