package fpoly.longlt.lab.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.longlt.lab.lab5.ui.theme.Lab1Theme
import java.time.format.TextStyle

class Bai3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab1Theme {
                CategoryApp()
            }
        }
    }
}

@Composable
fun Greeting6(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun CategoryApp() {
    val categories = listOf(
        "Fiction", "Mystery", "Science Fiction",
        "Fantasy", "Adventure", "Historical", "Horror", "Romance"
    )
    val suggestions = listOf(
        "Biography", "Cookbook", "Poetry", "Self-help",
        "Thriller"
    )

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Choose a category:", style =
            MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        AssistChip(
            onClick = { /* Do something */ },
            label = { Text("Need help?") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CategoryChips(categories, selectedCategories, onCategorySelected = { category ->
            selectedCategories = if (selectedCategories.contains(category))
                selectedCategories - category
            else
                selectedCategories + category
        })

        Spacer(modifier = Modifier.height(16.dp))

        SuggestionChips(suggestions, selectedCategories,
            onSuggestionSelected = { suggestion ->
                selectedCategories = selectedCategories + suggestion
            })

        if (selectedCategories.isNotEmpty()) {

            Spacer(modifier = Modifier.height(16.dp))

            SelectedCategoriesChips(selectedCategories, onCategoryRemoved =
            { category ->
                selectedCategories = selectedCategories - category
            })

            Spacer(modifier = Modifier.height(4.dp))

            AssistChip(
                onClick = { selectedCategories = setOf() },
                label = {
                    Text(
                        "Clear selections",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor
                    = Color.DarkGray
                ),
                border = AssistChipDefaults.assistChipBorder(
                    borderColor =
                    Color.Black
                )
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategories: Set<String>,
    onCategorySelected: (String) -> Unit
) {
    Text("Choose categories:", style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        categories.forEach { category ->
            FilterChip(
                selected = selectedCategories.contains(category),
                onClick = { onCategorySelected(category) },
                label = { Text(category) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Composable
fun SuggestionChips(
    suggestions: List<String>,
    selectedCategories: Set<String>,
    onSuggestionSelected: (String) -> Unit
) {
    Text("Suggestions:", style = MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        suggestions.forEach { suggestion ->
            val isSelected = selectedCategories.contains(suggestion)
            val chipColors = if (isSelected) {
                SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color.LightGray
                )
            } else {
                SuggestionChipDefaults.suggestionChipColors()
            }
            SuggestionChip(
                onClick = { onSuggestionSelected(suggestion) },
                label = { Text(suggestion) },
                colors = chipColors,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCategoriesChips(selectedCategories: Set<String>,
                            onCategoryRemoved: (String) -> Unit) {
    Text("Selected categories:", style =
    MaterialTheme.typography.titleMedium)
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        selectedCategories.forEach { selectedCategory ->
            InputChip(
                selected = true,
                onClick = { },
                label = { Text(selectedCategory) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Deselect",
                        modifier = Modifier
                            .clickable {
                                onCategoryRemoved(selectedCategory)
                            }
                            .size(18.dp)
                    )
                },
                modifier = Modifier.padding(end = 8.dp),
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    Lab1Theme {
        Greeting6("Android")
    }
}