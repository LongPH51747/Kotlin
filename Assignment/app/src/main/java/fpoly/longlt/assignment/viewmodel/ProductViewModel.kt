package fpoly.longlt.assignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpoly.longlt.assignment.model.Product
import fpoly.longlt.assignment.request.ProductRequest
import fpoly.longlt.assignment.response.toProduct
import fpoly.longlt.assignment.retrofit.RetrofitService
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    private val _issuccess = MutableLiveData<Boolean>()
    val issuccess: LiveData<Boolean> = _issuccess

    init {
        getProduct()
    }

    fun getProduct() {
        viewModelScope.launch {
            try {
                val reponse = RetrofitService().productService.getListProduct();
                if (reponse.isSuccessful) {
                    _products.postValue(reponse.body()?.map { it.toProduct() })
                } else {
                    _products.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products: ${e.message}")
                _products.value = emptyList()
            }
        }
    }

    fun getProductById(id: String?): LiveData<Product?> {
        val liveData = MutableLiveData<Product?>()
        id?.let {
            viewModelScope.launch {
                try {
                    val response = RetrofitService().productService.getProductById(id)
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()?.toProduct())
                    } else {
                        liveData.postValue(null)
                    }
                } catch (e: Exception) {
                    liveData.postValue(null)
                }
            }
        }
        return liveData
    }

    fun updateProduct(product: ProductRequest, id: String) {
        viewModelScope.launch {
            _issuccess.value = try {
                val response = RetrofitService().productService.updateProduct(product, id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getProduct()
                            true
                        } else {
                            false
                        }
                    } ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

    fun addProduct(product: ProductRequest) {
        viewModelScope.launch {
            _issuccess.value = try {
                val response = RetrofitService().productService.createProduct(product)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getProduct()
                            true
                        } else {
                            false
                        }
                    } ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch {
            _issuccess.value = try {
                val response = RetrofitService().productService.deleteProduct(id)
                if (response.isSuccessful) {
                    getProduct()
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }
}