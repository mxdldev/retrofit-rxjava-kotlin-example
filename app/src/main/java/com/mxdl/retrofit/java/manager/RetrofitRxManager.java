package com.mxdl.retrofit.java.manager;

import android.content.Context;
import android.text.TextUtils;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.retrofit.java.config.Config;
import com.mxdl.retrofit.java.service.CommonRxService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Description: <RetrofitCallManager><br>
 * Author:      mxdl<br>
 * Date:        2020/3/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RetrofitRxManager {
    private static RetrofitRxManager retrofitCallManager;
    private Retrofit retrofit;
    private String token;

    private RetrofitRxManager() {
        OkHttpClient httpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        if (!TextUtils.isEmpty(token)) {
                            request = request.newBuilder().url(
                                             request
                                            .url()
                                            .newBuilder()
                                            .addQueryParameter("access_token", token)
                                            .build()
                            ).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new LoggingInterceptor.Builder().setLevel(Level.BODY)
                        .log(Platform.INFO)
                        .request("request")
                        .response("reponse")
                        .build())
                .build();


        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.baseUrl)
                .build();
    }

    public static RetrofitRxManager getInstance(Context context) {
        if (retrofitCallManager == null) {
            synchronized (RetrofitRxManager.class) {
                if (retrofitCallManager == null) {
                    retrofitCallManager = new RetrofitRxManager();
                }
            }
        }
        return retrofitCallManager;
    }

    public CommonRxService getCommonRxService() {
        return retrofit.create(CommonRxService.class);
    }

    public void setToken(String token) {
        this.token = token;
    }
}
