package fpoly.longlt.assignment.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme
import fpoly.longlt.assignment.viewmodel.ProductViewModel

class AdminScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                val navController = rememberNavController()
                val productViewModel = ProductViewModel()
                ScreenAdmin(navController = navController, productViewModel = productViewModel)
            }
        }
    }
}

@Composable
fun ListViewAdmin(productList: List<Product>, onDetailClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(productList) { product ->
            Column(modifier = Modifier
                .width(160.dp)
                .height(255.dp)
                .clickable { onDetailClick(product.id) }) {
                AsyncImage(
                    model = product.img,
                    contentDescription = "image product",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomCenter,

                    )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(
                            text = "${product.name}",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = "$ ${product.price}",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                    Column() {
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(30.dp)) {
                            Icon(painter = painterResource(id = R.drawable.delete), contentDescription = "delete", modifier = Modifier.size(18.dp))
                        }
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(30.dp)) {
                            Icon(painter = painterResource(id = R.drawable.edit_2), contentDescription = "edit", modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenAdmin(navController: NavController, productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    val productState = productViewModel.products.observeAsState(initial = emptyList())
    val products = productState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Admin",
            fontFamily = FontFamily(Font(R.font.gelasio_medium)),
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        ListViewAdmin(productList = products,
            onDetailClick = { navController.navigate("${Screen.DETAILSCREEN.route}/${it}") })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    AssignmentTheme {}
}