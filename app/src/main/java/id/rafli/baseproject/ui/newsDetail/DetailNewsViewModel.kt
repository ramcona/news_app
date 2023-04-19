package id.rafli.baseproject.ui.newsDetail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.rafli.baseproject.base.BaseViewModel
import id.rafli.baseproject.model.News
import id.rafli.baseproject.network.getErrorMessage
import id.rafli.baseproject.repo.NewsRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class DetailNewsViewModel : ViewModel(), BaseViewModel<News> {
    override val response = MutableLiveData<News>()
    override val isLoading = MutableLiveData<Boolean>()
    override val errorMsg = MutableLiveData<String>()
    override var subscription: Disposable? = null

    private val repo = NewsRepo()

    fun getDetail(id: String) {
        isLoading.value = true
        subscription?.dispose()

        subscription = repo.getDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    isLoading.value = false
                    if (!result.error) {
                        response.value = result.data ?: News()
                    } else {
                        errorMsg.value = result.message
                    }
                },
                { error ->
                    isLoading.value = false
                    if (error is HttpException) {
                        val code = error.code()
                        errorMsg.value = getErrorMessage(code)
                    } else {
                        errorMsg.value = "Error: ${error.message}"
                    }
                }
            )
    }
}