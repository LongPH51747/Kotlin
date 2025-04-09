package fpoly.longlt.assignment.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme
import fpoly.longlt.assignment.viewmodel.ProductViewModel

class DetailScreen : ComponentActivity() {
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
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Composable
fun Detail(
    navController: NavController,
    id: String?,
    productViewModel: ProductViewModel,
) {
    val product = productViewModel.getProductById(id).observeAsState(initial = null).value
    var count by remember { mutableStateOf(0) }
    Log.d("Product: ", "${product}")
    product?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = "${it.img}",
                    contentDescription = "img product",
                    modifier = Modifier
                        .width(350.dp)
                        .height(455.dp)
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(bottomStart = 50.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopEnd
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 55.dp, start = 35.dp)
                        .shadow(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.TopStart)
                        .background(Color.White)
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "${it.name}",
                    fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                    fontSize = 25.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$${it.price}", fontSize = 30.sp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = { count++ },
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .width(30.dp)
                                .height(30.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp),
                                painter = painterResource(id = R.drawable.them),
                                contentDescription = "plus"
                            )
                        }
                        Text(
                            text = "${count}",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(15.dp, 0.dp)
                        )
                        IconButton(
                            onClick = { count-- },
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .width(30.dp)
                                .height(30.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp),
                                painter = painterResource(id = R.drawable.tru),
                                contentDescription = "tru"
                            )
                        }
                    }
                }
                Text(
                    text = "${it.description}",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(0.dp, 10.dp).height(150.dp),
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Justify
                )
                Row(
                    modifier = Modifier
                        .padding(0.dp, 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier

                            .width(60.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bookmark),
                            contentDescription = "bookmark icon",
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(250.dp)
                            .height(60.dp)
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}


data class ProductData(
    val id: String? = "null",
    val name: String = "null",
    val price: Double = 0.0,
    val img: String = "null",
    val description: String = "null",
    val category: String = "null"
)

//@Preview
//@Composable
//fun Detail() {
//    val navController = rememberNavController()
//    img(navController, "null", ProductViewModel(), "null")
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    AssignmentTheme {
        Greeting2("Android")
    }
}