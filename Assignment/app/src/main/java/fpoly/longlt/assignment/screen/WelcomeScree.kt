package fpoly.longlt.assignment.screen

import android.icu.number.Scale
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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

class WelcomeScree : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                BackGround()
            }
        }
    }
}

@Composable
fun Greeting5(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview
@Composable
fun BackGround() {
    val fontFamily = FontFamily(
        Font(R.font.gelasio_medium)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painterResource(id = R.drawable.bgr),
            contentDescription = "background image",
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp),
            ) {
                Text(text = "MAKE YOUR", fontFamily = fontFamily, fontSize = 25.sp, color = Color.Gray)
                Text(
                    text = "HOME BEAUTIFUL",
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 30.sp,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "The best simple place where you discover most wonderful furnitures and make your home beautiful",
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    color = Color.Gray,
                    lineHeight = 35.sp,
                    modifier = Modifier.width(285.dp),
                )
                Box(modifier = Modifier
                    .padding(top = 200.dp)
                    .width(160.dp)
                    .height(55.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Black)
                    ,
                    contentAlignment = Alignment.Center,
                    ){
                Text(
                    text = "Get Started",
                    fontFamily = fontFamily,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    AssignmentTheme {
        Greeting5("Android")
    }
}