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
import fpoly.longlt.assignment.model.Notifi
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme

class NotificationScreen : ComponentActivity() {
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
fun Greeting7(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun HeaderNotifi() {
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
fun NotifiActivity(products: List<Notifi>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 18.dp)
        ) {
            items(products) { item ->
                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = item.getImage()),
                                contentDescription = "img product",
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            Column(
                                modifier = Modifier.padding(start = 10.dp)
                            ) {
                                Text(
                                    text = "${item.getNameSP()}",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                )
                                Text(
                                    text = "Đơn hàng ${item.getOrder()} đang được vận chuyển",
                                    fontSize = 15.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(0.dp, 5.dp)
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
    }
}

@Preview
@Composable
fun previewNotifi() {
    var list = ArrayList<Notifi>()
    list.add(Notifi(1, "Black Simple Lamp", "#123456789", R.drawable.light))
    list.add(Notifi(2, "Black Simple Lamp", "#123456789", R.drawable.table))
    list.add(Notifi(3, "Black Simple Lamp", "#123456789", R.drawable.tu))
    list.add(Notifi(4, "Black Simple Lamp", "#123456789", R.drawable.chair))
    list.add(Notifi(5, "Black Simple Lamp", "#123456789", R.drawable.table))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HeaderNotifi()
        NotifiActivity(list)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    AssignmentTheme {
        Greeting7("Android")
    }
}