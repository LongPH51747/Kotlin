package fpoly.longlt.assignment.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.model.Category
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme
import kotlin.math.log

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                var list = ArrayList<Product>()
                list.add(Product(1, "Black Simple Lamp", 12.00, R.drawable.light, "Đẹp quá"))
                list.add(Product(2, "Minimal Stand", 25.00, R.drawable.table, "Đẹp quá"))
                list.add(Product(3, "Coffee Chair", 20.00, R.drawable.chair, "Đẹp quá"))
                list.add(Product(4, "Simple Desk", 50.00, R.drawable.tu, "Đẹp quá"))
                val menus = ArrayList<Category>()
                menus.add(Category(1, R.drawable.star, "Popular"))
                menus.add(Category(2, R.drawable.chairicon, "Chair"))
                menus.add(Category(3, R.drawable.tableicon, "Table"))
                menus.add(Category(4, R.drawable.sofar, "Armchair"))
                menus.add(Category(5, R.drawable.bed, "Bed"))
                Column {
                    headerHome()
                    horizontalList(menuList = menus)
                    vertivalList(productList = list)
                    tabarBottom(R.drawable.home, R.drawable.favourite, R.drawable.bell, R.drawable.person)
                }
            }
        }
    }
}

@Composable
fun headerHome() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "search icon",
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )

        Column {
            Text(text = "Make home", fontFamily = FontFamily(Font(R.font.gelasio_medium)), color = Color.Gray, fontSize = 20.sp)
            Text(text = "BEAUTIFUL", fontFamily = FontFamily(Font(R.font.gelasio_medium)), color = Color.Black, fontSize = 20.sp)
        }
        Image(
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "cart icon",
            modifier = Modifier
                .width(25.dp)
                .height(25.dp)
        )
    }
}

@Composable
fun horizontalList(menuList: List<Category>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        menuList.forEachIndexed { index, item ->
            Column(
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(red = 245, green = 245, blue = 245, alpha = 255)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = item.getImg()),
                        contentDescription = "menu icon",
                        modifier = Modifier
                            .width(28.dp)
                            .height(28.dp)
                    )
                }
                Text(text = "${item.getCategory()}", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }

}

@Composable
fun vertivalList(productList: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(
                bottom = 50.dp,
                start = 30.dp
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(productList) { product ->
            Column(
                modifier = Modifier
                    .width(160.dp)
                    .height(255.dp)
            ) {
                Image(
                    painter = painterResource(id = product.getImg()),
                    contentDescription = "image product",
                    modifier = Modifier
                        .width(160.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomCenter,

                    )
                Text(
                    text = "${product.getName()}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "$ ${product.getPrice()}",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
fun tabarBottom(home: Int, favourite: Int, notifi: Int, person: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = home), contentDescription = "home icon", modifier = Modifier
            .width(25.dp)
            .height(25.dp))
        Image(painter = painterResource(id = favourite), contentDescription = "favourite icon", modifier = Modifier
            .width(25.dp)
            .height(25.dp))
        Image(painter = painterResource(id = notifi), contentDescription = "bell icon", modifier = Modifier
            .width(25.dp)
            .height(25.dp))
        Image(painter = painterResource(id = person), contentDescription = "person icon", modifier = Modifier
            .width(25.dp)
            .height(25.dp))
    }
}
@Preview
@Composable
fun PreviewList() {
    val list = ArrayList<Product>()
    list.add(Product(1, "Black Simple Lamp", 12.00, R.drawable.light, "Đẹp quá"))
    list.add(Product(2, "Minimal Stand", 25.00, R.drawable.table, "Đẹp quá"))
    list.add(Product(3, "Coffee Chair", 20.00, R.drawable.chair, "Đẹp quá"))
    list.add(Product(4, "Simple Desk", 50.00, R.drawable.tu, "Đẹp quá"))
    val menus = ArrayList<Category>()
    menus.add(Category(1, R.drawable.star, "Popular"))
    menus.add(Category(2, R.drawable.chairicon, "Chair"))
    menus.add(Category(3, R.drawable.tableicon, "Table"))
    menus.add(Category(4, R.drawable.sofar, "Armchair"))
    menus.add(Category(5, R.drawable.bed, "Bed"))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        headerHome()
        horizontalList(menuList = menus)
        vertivalList(productList = list)
        tabarBottom(R.drawable.home, R.drawable.favourite, R.drawable.bell, R.drawable.person)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    AssignmentTheme {
    }
}