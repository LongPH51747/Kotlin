package fpoly.longlt.assignment.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.request.ProductFormData
import fpoly.longlt.assignment.response.toProductFormData
import fpoly.longlt.assignment.response.toProductRequest
import fpoly.longlt.assignment.screen.ui.theme.AssignmentTheme
import fpoly.longlt.assignment.viewmodel.ProductViewModel

class DataFormScreen : ComponentActivity() {
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
fun ScreenFormData(
    navController: NavController,
    productViewModel: ProductViewModel,
    id: String?,
) {
    val product = productViewModel.getProductById(id).observeAsState(initial = null).value
    val isEdit = id != null
    var formData by remember(product) {
        mutableStateOf(product?.toProductFormData() ?: ProductFormData())
    }       
    Spacer(modifier = Modifier.padding(top = 30.dp))
    ProductForm(formData = formData, onSave = {
        if (isEdit) {
            if (id != null) {
                productViewModel.updateProduct(formData.toProductRequest(), id)
            }
            navController.popBackStack()
        } else {
            productViewModel.addProduct(formData.toProductRequest())
            navController.popBackStack()
        }
    }) { updateformData ->
        formData = updateformData
        val isvalid =
            if (isEdit) {
                product?.let { validateProductDataAndEnsureCompletion(updateformData, it) }
                    ?: false
            } else isAllFieldsEntered(updateformData)
    }

}

@Composable
fun ProductForm(
    formData: ProductFormData,
    onSave: () -> Unit,
    onUpdateFromData: (ProductFormData) -> Unit,
) {
    val categoryList = listOf("light", "chair", "armchair", "bed", "table", "Lamp")
    var selectedCategory by remember {
        mutableStateOf(categoryList[0])
    }
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Spacer(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState())
                .imePadding()
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = formData.image,
            onValueChange = { onUpdateFromData(formData.copy(image = it)) },
            label = { Text(text = "Image URL của sản phẩm *") },
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.name,
            onValueChange = { onUpdateFromData(formData.copy(name = it)) },
            label = { Text(text = "Tên sản phẩm *") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.price,
            onValueChange = { onUpdateFromData(formData.copy(price = it)) },
            label = { Text(text = "Giá sản phẩm *") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formData.description,
            onValueChange = { onUpdateFromData(formData.copy(description = it)) },
            label = { Text(text = "Mô tả sản phẩm *") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        CustomSpinner(
            selectedCategory = formData.category,
            onSelectedCategory = { newCategory -> onUpdateFromData(formData.copy(category = newCategory)) },
            categoryList = categoryList
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { onSave() }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSpinner(
    selectedCategory: String,
    onSelectedCategory: (String) -> Unit,
    categoryList: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            label = { Text("Danh mục sản phẩm *") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            categoryList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelectedCategory(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

fun isAllFieldsEntered(formData: ProductFormData): Boolean {
    return with(formData) {
        name.isNotEmpty() && image.isNotEmpty() && category.isNotEmpty() &&
                price.isNotEmpty() && description.isNotEmpty()
    }
}

fun validateProductDataAndEnsureCompletion(formData: ProductFormData, product: Product): Boolean {
    if (!isAllFieldsEntered(formData)) return false
    if (formData.name != product.name) return true
    if (formData.category != product.category) return true
    if (formData.price != product.price) return true
    if (formData.description != product.description) return true
    if (formData.image != product.image) return true

    return false
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    AssignmentTheme {
    }
}