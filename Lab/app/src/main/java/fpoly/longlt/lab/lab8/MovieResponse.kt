package fpoly.longlt.lab.lab8

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: String,
    @SerializedName("filmName") val filmName: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("national") val national: String,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
)

    fun MovieResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        filmName = this.filmName,
        duration = this.duration,
        releaseDate = this.releaseDate,
        genre = this.genre,
        national = this.national,
        description = this.description,
        image = this.image
    )
}
fun MovieFormData.toMovieRequest(): MovieRequest {
    val filmIdInt = try {
        this.id?.toIntOrNull()
    } catch (e: NumberFormatException) {
        null
    }
    val durationInt = try {
        this.duration.toInt()
    } catch (e: NumberFormatException) {
        0
    }
    return MovieRequest(
        id = filmIdInt,
        filmName = this.filmName,
        duration = durationInt,
        releaseDate = this.releaseDate,
        genre = this.genre,
        national = this.national,
        description = this.description,
        image = this.imageUrl
    )
}

fun Movie?.toMovieFormData() = this?.let {
    MovieFormData(
        this.id,
        this.filmName,
        this.duration,
        this.releaseDate,
        this.genre,
        this.national,
        this.description,
        this.image
    )
}
