package fpoly.longlt.assignment.service

import fpoly.longlt.assignment.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("product")
    suspend fun getListProduct():Response<List<ProductResponse>>
    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id:String):Response<ProductResponse>

}