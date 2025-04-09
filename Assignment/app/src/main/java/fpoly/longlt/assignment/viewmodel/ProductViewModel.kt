package fpoly.longlt.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.response.toProduct
import fpoly.longlt.assignment.retrofit.RetrofitService
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val     products: LiveData<List<Product>> = _products

    init {
        getProduct()
    }

    fun getProduct(){
        viewModelScope.launch {
            try {
                val reponse = RetrofitService().productService.getListProduct();
                if (reponse.isSuccessful){
                    _products.postValue(reponse.body()?.map { it.toProduct() })
                } else{
                    _products.postValue(emptyList())
                }
            } catch (e: Exception){
                Log.e("ProductViewModel", "Error fetching products: ${e.message}")
                _products.value = emptyList()
            }
        }
    }

    fun getProductById(id: String?): LiveData<Product?>{
        val liveData = MutableLiveData<Product?>()
        id?.let {
            viewModelScope.launch {
                try {
                    val response = RetrofitService().productService.getProductById(id)
                    if (response.isSuccessful){
                        liveData.postValue(response.body()?.toProduct())
                    } else{
                        liveData.postValue(null)
                    }
                } catch (e: Exception){
                    liveData.postValue(null)
                }
            }
        }
        return liveData
    }

}