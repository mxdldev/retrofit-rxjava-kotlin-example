package com.mxdl.retrofit.java.service;

import com.mxdl.retrofit.java.entity.Token;
import com.mxdl.retrofit.java.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Description: <CommonCallService><br>
 * Author:      mxdl<br>
 * Date:        2020/3/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface CommonCallService {
    @POST("/oauth/token")
    Call<Token> getToken(@Header("Authorization") String authorization, @Query("grant_type") String grant_type, @Query("username")String username, @Query("password")String password);

    @POST("/users/user")
    Call<User> createUser(@Body User user);
}
