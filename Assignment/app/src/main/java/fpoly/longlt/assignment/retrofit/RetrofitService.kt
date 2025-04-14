package fpoly.longlt.assignment.retrofit

import fpoly.longlt.assignment.service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RetrofitService {
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.24.51.44:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val productService: ProductService = retrofit.create(ProductService::class.java)
}