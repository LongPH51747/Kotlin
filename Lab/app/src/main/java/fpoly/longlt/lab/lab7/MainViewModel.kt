package fpoly.longlt.lab.lab7

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpoly.longlt.lab.lab8.Movie
import fpoly.longlt.lab.lab8.MovieRequest
import fpoly.longlt.lab.lab8.RetrofitService
import fpoly.longlt.lab.lab8.toMovie
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                val reponse = RetrofitService().movieService.getListFilm()
                if (reponse.isSuccessful) {
                    _movies.postValue(reponse.body()?.map { it.toMovie() })
                } else {
                    _movies.postValue(emptyList())
                }
            } catch (e: Exception) {
                Log.e("Tag", "getMovie" + e.message)
                _movies.postValue(emptyList())
            }
        }
    }

    fun addFilm(movieRequest: MovieRequest) {
        viewModelScope.launch {
            _isSuccess.value = try {
                val response = RetrofitService().movieService.addFilm(movieRequest)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getMovies()
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

    fun updateMovie(movieRequest: MovieRequest, id: String) {
        viewModelScope.launch {
            _isSuccess.value = try {
                val response = RetrofitService().movieService.updateFilm(movieRequest,
                    id
                )
                Log.e("IdRequest: ", id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.status == 1) {
                            getMovies()
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

    fun getMovieById(filmId: String?): LiveData<Movie?> {
        val liveData = MutableLiveData<Movie?>()
        filmId?.let {
            viewModelScope.launch {
                try {
                    val response = RetrofitService().movieService.getFilmDetail(filmId)
                    if (response.isSuccessful) {
                        liveData.postValue(response.body()?.toMovie())
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

    fun deleteMovieById(id: String) {
        viewModelScope.launch {
            _isSuccess.value = try {
                // Kiểm tra xem RetrofitService đã được khởi tạo và sử dụng đúng cách
                val response = RetrofitService().movieService.deleteFilm(id)

                // Kiểm tra nếu response thành công
                if (response.isSuccessful) {
                    // Xử lý khi có body trả về
                    getMovies() // Lấy lại danh sách phim sau khi xóa
                    true
                } else {
                    // In ra thông báo lỗi nếu response không thành công
                    Log.e("DeleteMovie", "Error: ${response.code()} - ${response.message()}")
                    false
                }
            } catch (e: Exception) {
                // Log ra thông tin lỗi khi gặp exception
                Log.e("DeleteMovie", "Exception: ${e.localizedMessage}")
                false
            }
        }
    }

}



