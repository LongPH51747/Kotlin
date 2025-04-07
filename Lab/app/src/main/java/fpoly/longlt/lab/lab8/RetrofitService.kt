package fpoly.longlt.lab.lab8

import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService {
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.1.12:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val movieService: MovieService by lazy { retrofit.create(MovieService::class.java) }
}