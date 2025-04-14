package fpoly.longlt.assignment.service

import fpoly.longlt.assignment.request.ProductRequest
import fpoly.longlt.assignment.request.StatusResponse
import fpoly.longlt.assignment.response.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductService {
    @GET("product")
    suspend fun getListProduct():Response<List<ProductResponse>>
    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id:String):Response<ProductResponse>
    @PUT("product/{id}")
    suspend fun updateProduct(@Body productRequest: ProductRequest, @Path("id") id:String):Response<StatusResponse>
    @POST("product")
    suspend fun createProduct(@Body productRequest: ProductRequest):Response<StatusResponse>
    @DELETE("product/{id}")
    suspend fun deleteProduct(@Path("id") id:String):Response<StatusResponse>

}