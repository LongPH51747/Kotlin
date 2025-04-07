package fpoly.longlt.lab.lab8

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie")
    suspend fun getListFilm(): Response<List<MovieResponse>>
    @GET("movie/{id}")
    suspend fun getFilmDetail(@Path("id") id: String): Response<MovieResponse>
    @POST("movie")
    suspend fun addFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>
    @PUT("movie/{id}")
    suspend fun updateFilm(@Body filmRequest: MovieRequest, @Path("id") id: String): Response<StatusResponse>
    @DELETE("movie/{id}")
    suspend fun deleteFilm(@Path("id") id: String): Response<StatusResponse>
}