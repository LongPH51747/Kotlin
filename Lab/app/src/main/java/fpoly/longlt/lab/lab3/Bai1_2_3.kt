package fpoly.longlt.lab.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpoly.longlt.lab.lab3.ui.theme.Lab1Theme

class Bai1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 50.dp, 0.dp, 0.dp)
                ) {
                    GreetingText(name = "Bai 2:")
                    GreetingCard(msg = "Hello, Long")
                    GreetingText(name = "Bai 3:")
                    CounterCard()
                }
            }
        }
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = name,
        fontSize = 20.sp,
        color = Color.Black
    )
}

@Composable
fun GreetingCard(msg: String) {
    var text by rememberSaveable { mutableStateOf(msg) }
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GreetingText(name = text)
        Button(
            onClick = { text = "Hi" },
            modifier = Modifier
                .padding(10.dp)
                .width(300.dp),
        ) {
            Text(
                text = "Say Hi",
                fontSize = 20.sp,
            )
        }
    }
}
@Composable
fun CounterCard(){
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GreetingText(name = "You have clicked the button ${count} times.")
        Button(
            onClick = { count++ },
            modifier = Modifier
                .padding(10.dp)
                .width(300.dp),
        ){
            Text(
                text = "Click Me",
                fontSize = 20.sp,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1Theme {
        GreetingCard(msg = "Hello, Long")
    }
}