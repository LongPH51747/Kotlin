package fpoly.longlt.lab.lab4

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.longlt.lab.lab4.ui.theme.Lab1Theme

class Bai3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add"
                            )
                        }
                    }
                ) { innerPadding ->
                    NoteApp(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun NoteApp(PaddingValues: PaddingValues) {
    val note = listOf("Note 1", "Note 2", "Note 3", "Note 4", "Note 5")
    Column(
        Modifier
            .padding(PaddingValues)
            .padding(8.dp),
    ) {
        note.forEach { note ->
            NoteCard(noteText = note)
        }
    }
}

@Composable
fun NoteCard(noteText: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.Cyan, shape = MaterialTheme.shapes.medium)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = noteText,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand note",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Lab1Theme {
        Greeting3("Android")
    }
}