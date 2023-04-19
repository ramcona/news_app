package id.rafli.baseproject.network

import id.rafli.baseproject.base.App.Companion.pref
import id.rafli.baseproject.helper.Config.BASE_API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ClientService {

    fun create(file: Boolean, isLogin:Boolean = true): ApiServiceServer {
        var cTO:Long = 30
        var wTO:Long = 30
        var rTO:Long = 30

        if (file){
            cTO = 120
            wTO = 120
            rTO = 120
        }

        val interceptor = HttpLoggingInterceptor(Logger())
        interceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient =  OkHttpClient.Builder()
        okHttpClient.connectTimeout(cTO, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(wTO, TimeUnit.SECONDS)
        okHttpClient.readTimeout(rTO, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(HttpLoggingInterceptor(Logger()).apply { level = HttpLoggingInterceptor.Level.BODY })

        if (isLogin){
            okHttpClient.addInterceptor(AuthInterceptor(pref.getAccessToken()))
        }

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(BASE_API)
            .client(okHttpClient.build())
            .build()


        return retrofit.create(ApiServiceServer::class.java)
    }

}