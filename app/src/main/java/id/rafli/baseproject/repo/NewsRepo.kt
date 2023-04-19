package id.rafli.baseproject.repo

import id.rafli.baseproject.base.BaseHelper
import id.rafli.baseproject.base.BaseResponse
import id.rafli.baseproject.model.News
import io.reactivex.Observable


class NewsRepo: BaseHelper() {

    fun getNews(): Observable<BaseResponse<List<News>>> {
        return  ApiServiceServer.getNews()
    }

    fun getDetail(id:String): Observable<BaseResponse<News>> {
        return ApiServiceServer.getDetailNews(id)
    }
}