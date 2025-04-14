package fpoly.longlt.assignment.screen

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
            }
        }
    }
}

@Composable
fun ListViewAdmin(
    productList: List<Product>,
    onDetailClick: (String) -> Unit,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 60.dp, top = 10.dp)
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
                    model = product.image,
                    contentDescription = "image product",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomCenter,

                    )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
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
                        IconButton(
                            onClick = { onDeleteClick(product.id) },
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = "delete",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        IconButton(
                            onClick = { onEditClick(product.id) },
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.edit_2),
                                contentDescription = "edit",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ScreenAdmin(navController: NavController, productViewModel: ProductViewModel) {
    val productState = productViewModel.products.observeAsState(initial = emptyList())
    val deleteSuccess by productViewModel.issuccess.observeAsState()
    LaunchedEffect(Unit) {
        productViewModel.getProduct()
    }

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
        Button(
            onClick = { navController.navigate(Screen.ADD.route) },
            modifier = Modifier
                .padding(top = 20.dp)
                .widthIn(min = 100.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Thêm sản phẩm", fontSize = 15.sp)
        }
        ListViewAdmin(productList = products,
            onDetailClick = { navController.navigate("${Screen.DETAILSCREEN.route}/${it}") },
            onEditClick = { navController.navigate("${Screen.EDIT.route}/${it}") },
            onDeleteClick = { productViewModel.deleteProduct(it) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    AssignmentTheme {}
}