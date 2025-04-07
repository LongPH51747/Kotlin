package fpoly.longlt.lab.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import fpoly.longlt.lab.lab7.ui.theme.Lab1Theme
import fpoly.longlt.lab.lab8.Movie
import fpoly.longlt.lab.lab8.MovieFormScreen

class Bai2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                ScreenNavigation()

            }

        }
    }
}

@Composable
fun MovieScreen(navigationController: NavController, movieViewModel: MovieViewModel) {

    val movieViewModel: MovieViewModel = viewModel()
    val moviesState = movieViewModel.movies.observeAsState(initial = emptyList())
    var movies = moviesState.value
    LaunchedEffect(Unit) {
        movieViewModel.getMovies()
    }
    Column {
        Button(onClick = {
            navigationController.navigate(Screen.ADD.route)
        }) {
            Text("Thêm")
        }

        MovieColumn(movies,
            onEditClick = {
                navigationController.navigate("${Screen.EDIT.route}/${it}")
            },
            onDeleteClick = {
                movieViewModel.deleteMovieById(it)
            })
    }
}

@Composable
fun MovieColumn(
    movies: List<Movie>,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 50.dp)
    ) {
        items(movies.size) { index ->
            MovieColumnItem(
                movie = movies[index],
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
fun BoldValueText(
    label: String,
    value: String,
    style: TextStyle = MaterialTheme.typography.bodySmall
) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}

@Composable
fun MovieColumnItem(
    movie: Movie,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(130.dp)
                    .wrapContentHeight()
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = movie.filmName,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(label = "Thời lượng: ", value = movie.duration)
                BoldValueText(label = "Khởi chiếu: ", value = movie.releaseDate)
                BoldValueText(label = "Thể loại: ", value = movie.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = movie.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(end = 4.dp)
                ) {
                    IconButton(
                        onClick = { onEditClick(movie.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    IconButton(
                        onClick = { onDeleteClick(movie.id) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Greeting10(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val movieViewModel: MovieViewModel = viewModel()
//    val movieState = movieViewModel.movies.observeAsState(initial = emptyList())
    NavHost(navController = navController, startDestination = Screen.MOVIE_SCREEN.route) {
        composable(Screen.LOGIN.route) {
            LoginScreen(navController)
        }
        composable(Screen.ADD.route) { MovieFormScreen(navController, movieViewModel, null) }
        composable(
            "${Screen.EDIT.route}/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmId")?.let { filmId ->
                MovieFormScreen(navController, movieViewModel, filmId)
            }
        }
        composable(Screen.MOVIE_SCREEN.route) {
            MovieScreen(navController, movieViewModel)
        }
        composable(Screen.SCREEN1.route) {
            Screen1(navController)
        }
        composable(Screen.SCREEN2.route) {
            Screen2(navController)
        }
        composable(Screen.SCREEN3.route) {
            Screen3(navController)
        }
    }
}

@Composable
fun Screen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .clickable {
                navController.navigate(Screen.SCREEN2.route) {
                    popUpTo(Screen.SCREEN1.route) { inclusive = true }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 1", color = Color.White, fontSize = 20.sp)
    }
}

@Composable
fun Screen2(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .clickable { navController.navigate(Screen.SCREEN3.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 2", color = Color.Black, fontSize = 20.sp)
    }
}

@Composable
fun Screen3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
            .clickable { navController.navigate(Screen.SCREEN1.route) },
        contentAlignment = Alignment.Center
    ) {
        Text("Screen 3", color = Color.White, fontSize = 20.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    Lab1Theme {
        Greeting10("Android")
    }
}