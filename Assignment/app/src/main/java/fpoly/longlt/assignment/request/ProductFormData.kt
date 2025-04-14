package fpoly.longlt.assignment.request

data class ProductFormData(
    val id: String? = "",
    val name: String = "",
    val description: String = "",
    val price: String = "",
    val image: String = "",
    val category: String = "",
)
