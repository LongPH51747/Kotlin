package fpoly.longlt.assignment.response

import com.google.gson.annotations.SerializedName
import fpoly.longlt.assignment.model.Product

data class ProductResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String

)

fun ProductResponse.toProduct(): Product {
    return Product(
        id = this.id,
        name = this.name,
        price = this.price,
        img = this.image,
        description = this.description,
        category = this.category
    )
}
