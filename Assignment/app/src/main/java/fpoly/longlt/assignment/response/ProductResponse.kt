package fpoly.longlt.assignment.response

import com.google.gson.annotations.SerializedName
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.request.ProductFormData
import fpoly.longlt.assignment.request.ProductRequest

data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String

)

fun ProductResponse.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        image = this.image,
        description = this.description,
        category = this.category
    )
}

fun ProductFormData.toProductRequest(): ProductRequest {
    val productId = try {
        this.id?.toIntOrNull()
    } catch (e: Exception) {
        null
    }
    val price = try {
        this.price.toDouble()
    } catch (e: Exception) {
        0.0
    }
    return ProductRequest(
        id = productId,
        name = this.name,
        description = this.description,
        price = price,
        image = this.image,
        category = this.category
    )
}

fun Product?.toProductFormData() = this?.let {
    ProductFormData(
        this.id,
        this.name,
        this.description,
        this.price,
        this.image,
        this.category
    )
}
