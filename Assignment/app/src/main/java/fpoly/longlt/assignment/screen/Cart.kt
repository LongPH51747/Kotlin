package fpoly.longlt.assignment.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme

class Cart : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting6(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Composable
fun HeaderCart() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(18.dp, end = 18.dp, top = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "search icon"
        )
        Text(
            text = "My Cart", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.gelasio_medium))
        )
        Text(text = "")
    }
}

@Composable
fun CartActivity(products: List<Product>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(bottom = 120.dp)
        ) {
            items(products) { item ->
                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = item.getImg()),
                                contentDescription = "img product",
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier.padding(20.dp, 0.dp)
                            ) {
                                Text(
                                    text = "${item.getName()}", fontSize = 18.sp, color = Color.Gray
                                )
                                Text(
                                    text = "$${item.getPrice()}",
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(0.dp, 5.dp)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.width(115.dp).padding(top = 5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    IconButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .clip(
                                                RoundedCornerShape(8.dp)
                                            )
                                            .background(Color(0xfff0f0f0))
                                            .width(30.dp)
                                            .height(30.dp)
                                    ) {
                                        Icon(Icons.Filled.Add, contentDescription = "add icon")
                                    }
                                    Text(
                                        text = "01",
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(0.dp, 5.dp)
                                    )
                                    IconButton(
                                        onClick = { /*TODO*/ },
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xfff0f0f0))
                                            .width(30.dp)
                                            .height(30.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.tru),
                                            contentDescription = "delete icon",
                                            modifier = Modifier
                                                .width(14.dp)
                                                .height(14.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier.height(100.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .background(Color.White)
                                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                                    .width(20.dp)
                                    .height(20.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Close,
                                    contentDescription = "delete icon",
                                    modifier = Modifier
                                        .width(15.dp)
                                        .height(15.dp)
                                )
                            }
                        }

                    }
                    Spacer(
                        modifier = Modifier
                            .padding(0.dp, 20.dp)
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(Color(0xfff0f0f0))
                    )
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Text(text = "Total:", fontSize = 20.sp)
                Text(text = "$${products.sumOf { it.getPrice() }}", fontSize = 20.sp)
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(18.dp, 30.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Check out", fontSize = 20.sp)
            }
        }
    }
}

@Preview
@Composable
fun previewCart() {
    var list = ArrayList<Product>()
    list.add(Product(1, "Black Simple Lamp", 12.00, R.drawable.light, "Đẹp quá"))
    list.add(Product(2, "Minimal Stand", 25.00, R.drawable.table, "Đẹp quá"))
    list.add(Product(3, "Coffee Chair", 20.00, R.drawable.chair, "Đẹp quá"))
    list.add(Product(4, "Simple Desk", 50.00, R.drawable.tu, "Đẹp quá"))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HeaderCart()
        CartActivity(list)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    AssignmentTheme {
        Greeting6("Android")
    }
}