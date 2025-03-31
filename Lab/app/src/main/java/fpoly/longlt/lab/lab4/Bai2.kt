package fpoly.longlt.lab.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.longlt.lab.lab4.ui.theme.Lab1Theme
import fpoly.longlt.lab1.R

class Bai2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                val imgList = listOf(R.drawable.i1, R.drawable.i2, R.drawable.i3)
                Column(){
                    Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Icon")
                    HorizontalImageList(imgList)
                    VerticalImageList(imgList)
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun HorizontalImageList(imgList: List<Int>) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .padding(16.dp),
    ) {
        imgList.forEachIndexed { index, img ->
            Image(
                painter = painterResource(id = img),
                contentDescription = "Image description",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(
                        start = if (index == 0) 0.dp else 8.dp,
                        end = 8.dp
                    )
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}

@Composable
fun VerticalImageList(imgList: List<Int>) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        imgList.forEachIndexed { index, img ->
            Image(
                painter = painterResource(id = img),
                contentDescription = "Image description",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.padding(top = if (index==0) 0.dp else 8.dp,
                    bottom = 8.dp)
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewHorizontalImageList() {
    HorizontalImageList(listOf(R.drawable.i1, R.drawable.i2,
        R.drawable.i3))
}
@Preview(showBackground = true)
@Composable
fun PreviewVerticalImageList() {
    VerticalImageList(listOf(R.drawable.i1, R.drawable.i2,
        R.drawable.i3))
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Lab1Theme {
        Greeting2("Android")
    }
}