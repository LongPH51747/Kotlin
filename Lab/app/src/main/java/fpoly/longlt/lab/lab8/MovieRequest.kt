package fpoly.longlt.lab.lab8

data class MovieRequest(
    val id: Int? = null,
    val filmName: String,
    val duration: Int,
    val releaseDate: String,
    val genre: String,
    val national: String,
    val description: String,
    val image: String
)
data class StatusResponse(
    val status: Int,
    val message: String
)