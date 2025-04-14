package fpoly.longlt.assignment.request

import com.google.gson.annotations.SerializedName

data class ProductRequest(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val category: String
)
data class StatusResponse(
 val status:Int,
 val message: String
)
