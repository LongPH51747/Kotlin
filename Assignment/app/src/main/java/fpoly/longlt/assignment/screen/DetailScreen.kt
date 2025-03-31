package fpoly.longlt.assignment.screen

import android.os.Bundle
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
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme

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
fun img(img: Int, name: String, price: Int, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = img),
                contentDescription = "img product",
                modifier = Modifier
                    .width(335.dp)
                    .height(455.dp)
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(bottomStart = 50.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopEnd
            )
            IconButton(
                onClick = { /* TODO: Xử lý nút back */ },
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
                text = name, fontFamily = FontFamily(Font(R.font.gelasio_medium)), fontSize = 25.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "$${price}", fontSize = 30.sp)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { /* TODO: Xử lý nút back */ },
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
                            contentDescription = "Back"
                        )
                    }
                    Text(text = "01", fontSize = 20.sp, modifier = Modifier.padding(15.dp, 0.dp))
                    IconButton(
                        onClick = { /* TODO: Xử lý nút back */ },
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
                            contentDescription = "Back"
                        )
                    }
                }
            }
            Text(
                text = description,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(0.dp, 10.dp),
                lineHeight = 22.sp,
                textAlign = TextAlign.Justify
            )
            Row(
                modifier = Modifier.padding(0.dp, 20.dp).fillMaxWidth(),
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

@Preview
@Composable
fun Detail() {
    img(
        R.drawable.table,
        "Minimal Stand",
        50,
        "Minimal Stand is made of by natural wood. The design that is very simple and minimal. This is truly one of the best furnitures in any family for now. With 3 different colors, you can easily select the best match for your home. "
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    AssignmentTheme {
        Greeting2("Android")
    }
}