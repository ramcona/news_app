package id.rafli.baseproject.network

import id.rafli.baseproject.base.BaseResponse
import id.rafli.baseproject.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceServer {

    /*news*/
    @GET("news/{id}")
    fun getDetailNews(
        @Path("id") id:String
    ): Observable<BaseResponse<News>>

    @GET("news")
    fun getNews(
    ): Observable<BaseResponse<List<News>>>

}