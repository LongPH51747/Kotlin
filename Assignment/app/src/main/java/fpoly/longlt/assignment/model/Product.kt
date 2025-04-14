package fpoly.longlt.assignment.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("image") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String
)