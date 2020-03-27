package com.mxdl.retrofit.kotlin.manager

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.mxdl.retrofit.kotlin.config.Config
import com.mxdl.retrofit.kotlin.service.CommonService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Description: <RetrofitManager><br>
 * Author:      mxdl<br>
 * Date:        2020/3/27<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
class RetrofitManager private constructor() {
    var retrofit: Retrofit? = null
    var token: String? = null

    init {
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BODY)
                    .log(Platform.INFO)
                    .request("request")
                    .response("response")
                    .build()
            )
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    var requst = chain.request()
                    if(token != null){
                        requst = requst
                            .newBuilder()
                            .url(
                                requst.url.newBuilder()
                                    .addQueryParameter("access_token",token)
                                    .build()
                            ).build()

                    }
                    return  chain.proceed(requst)
                }
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(Config.baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        var retrofitManager: RetrofitManager? = null;
        fun getInstance(): RetrofitManager? {
            if (retrofitManager == null) {
                synchronized(RetrofitManager::class.java) {
                    if (retrofitManager == null) {
                        retrofitManager = RetrofitManager()
                    }
                }
            }
            return retrofitManager
        }
    }
    fun getCommonService(): CommonService{
        return retrofit!!.create(CommonService::class.java)
    }
}