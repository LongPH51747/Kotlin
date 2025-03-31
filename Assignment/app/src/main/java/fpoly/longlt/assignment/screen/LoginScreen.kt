package fpoly.longlt.assignment.screen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpoly.longlt.assignment.R
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                LoginActivity()
            }
        }
    }
}

@Composable
fun TextView(hello: String, modifier: Modifier = Modifier) {
    Text(
        text = "$hello!",
        modifier = modifier
    )
}

@Preview
@Composable
fun LoginActivity() {
    var username = ""
    var password = ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.padding(top = 85.dp).align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(end = 30.dp)
                    .width(115.dp),
            )
            Image(
                painter = painterResource(id = R.drawable.logoicon),
                contentDescription = "logoicon",
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .width(115.dp),
            )
        }
        Column(
            modifier = Modifier.padding(top = 35.dp, start = 30.dp),
        ) {
            Text(text = "Hello !",
                fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                color = Color.Gray, fontSize = 30.sp)
            Text(
                text = "Welcome Back",
                fontFamily = FontFamily(Font(R.font.gelasio_medium)),
                color = Color.Black,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Surface(
            shadowElevation = 8.dp,
            color = Color.White,
            modifier = Modifier
                .padding(top = 35.dp)

        ) {
            Column(
                modifier = Modifier.width(350.dp)
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    modifier = Modifier
                        .padding(start = 35.dp, top = 20.dp)
                        .width(350.dp)
                        .height(80.dp),
                    label = { Text("Username") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .padding(start = 35.dp, top = 20.dp, bottom = 50.dp)
                        .height(80.dp)
                        .width(350.dp),
                    label = { Text("Password") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                    )
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Forgot Password",
                        modifier = Modifier.padding(bottom = 30.dp),
                        fontSize = 18.sp
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .width(285.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Login", fontSize = 18.sp)
                    }
                    Text(
                        text = "SIGN UP",
                        modifier = Modifier.padding(bottom = 30.dp).clickable { println("clicked") },
                        fontSize = 18.sp
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    AssignmentTheme {
        TextView("Android")
    }
}