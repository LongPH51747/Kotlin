package fpoly.longlt.lab.lab4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.longlt.lab.lab4.ui.theme.Lab1Theme
import fpoly.longlt.lab1.R
import kotlin.reflect.typeOf

class Bai1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                LoginSccreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun LoginSccreen() {
    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val content = LocalContext.current
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Logo")
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = username, onValueChange = {username = it}, label = { Text("Username")})
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = password, visualTransformation = PasswordVisualTransformation(), onValueChange = {password = it}, label = { Text("Password")})
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                Toast.makeText(content, "Enter Username and Password", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(content, "Login Success", Toast.LENGTH_SHORT).show()
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
            ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Lab1Theme {
        Greeting("Android")
    }
}