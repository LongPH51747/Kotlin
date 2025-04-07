package fpoly.longlt.lab.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fpoly.longlt.lab.lab6.ui.theme.Lab1Theme
import fpoly.longlt.lab.lab7.MovieViewModel
import fpoly.longlt.lab.lab8.Movie

class Bai1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                MovieScreen()
            }
        }
    }
}

@Composable
fun Greeting7(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

data class Movie(
    val title: String,
    val year: String,
    val posterUrl: String,
    val description: String,
    val time: Int
) {
    companion object {
        fun getSampleMovies() = listOf(
            Movie("Kung fu panda", "2015", "https://th.bing.com/th/id/OIP.BegFroawMzvbP0gNTwyAKAAAAA?rs=1&pid=ImgDetMain", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsumm", 180),
            Movie("Kinh Cong Đảo đầu lâu phần 4", "2017", "https://th.bing.com/th/id/R.544b1e7e299878fa4bc7f30e1c729ad7?rik=NpVEvWCmeRM%2fOQ&pid=ImgRaw&r=0", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", 120),
            Movie("The Dark Knight", "2008", "https://m.media-amazon.com/images/I/51pO34iN8rL._AC_.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", 130),
            Movie("Pulp Fiction", "1994", "https://m.media-amazon.com/images/I/51tEjqcEHrL._AC_.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", 123),
            Movie("Forrest Gump", "1994", "https://m.media-amazon.com/images/I/61c0G0R4u1L._AC_SL1024_.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", 125),
            Movie("Inception", "2010", "https://m.media-amazon.com/images/I/51oD1Lx8V-L._AC_.jpg", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum", 155),
        )
    }
}

@Composable
fun MovieItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier
                .width(175.dp)
                .heightIn(min = 330.dp)
                .then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(255.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topEnd = 8.dp, topStart =
                            8.dp
                        )
                    ),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movie.filmName, style =
                    MaterialTheme.typography.titleSmall, maxLines = 2
                )
                Text(
                    text = "Năm xuất bản: ${movie.releaseDate}", style =
                    MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MovieColumnItem(movie: Movie, listType: ListType) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .then(getItemSizeModifier(listType))
        ) {
            AsyncImage(
                model = movie.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .clip(
                        RoundedCornerShape(
                            topEnd = 8.dp, topStart =
                            8.dp
                        )
                    ),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = movie.filmName, style =
                    MaterialTheme.typography.titleSmall, maxLines = 2
                )
                Text(
                    text = "Năm xuất bản: ${movie.releaseDate}", style =
                    MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Thời lượng: ${movie.duration}", style =
                    MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Tóm tắt nội dung: ${"\n"} ${movie.description}", style =
                    MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5
                )
            }
        }
    }
}

@Composable
fun MovieColumn(movies: List<Movie>) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(movies.size) { index ->
            MovieColumnItem(movie = movies[index], ListType.COLUMN)
        }
    }

}

@Composable
fun MovieRow(movies: List<Movie>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], ListType.ROW)
        }
    }
}
@Composable
fun MovieGrid(movies: List<Movie>) {
    LazyVerticalStaggeredGrid(
        state = rememberLazyStaggeredGridState(),
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], ListType.GRID)
        }
    }
}



enum class ListType{
    ROW, COLUMN, GRID
}

@Composable
fun MovieScreen() {
    val movieViewModel: MovieViewModel = viewModel()
    val moviesState = movieViewModel.movies.observeAsState(initial = emptyList())
    val movies = moviesState.value
    var listType by remember { mutableStateOf(ListType.ROW) }
    Column {
        Row(
            modifier = Modifier
                .padding(8.dp, top = 50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { listType = ListType.ROW }) {
                Text("Row")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.COLUMN }) {
                Text("Column")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { listType = ListType.GRID }) {
                Text("Grid")
            }
        }
        when (listType) {
            ListType.ROW -> MovieRow(movies)
            ListType.COLUMN -> MovieColumn(movies)
            ListType.GRID -> MovieGrid(movies)
        }
    }
}

@Composable
private fun getItemSizeModifier(listType: ListType): Modifier {
    return when (listType) {
        ListType.ROW -> Modifier.width(175.dp)
        ListType.COLUMN -> Modifier
            .width(130.dp)

        ListType.GRID -> Modifier
            .fillMaxWidth()
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    Lab1Theme {
        Greeting7("Android")
    }
}