package com.mxdl.retrofit.api.http;

import android.content.Context;
import android.text.TextUtils;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mxdl.retrofit.api.CommonService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: <RetrofitManager><br>
 * Author:      mxdl<br>
 * Date:        2020/3/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class RetrofitManager {
    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private String token;

    private RetrofitManager() {
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
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.baseUrl)
                .build();
    }

    public static RetrofitManager getInstance(Context context) {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public CommonService getCommonService() {
        return retrofit.create(CommonService.class);
    }

    public void setToken(String token) {
        this.token = token;
    }
}
