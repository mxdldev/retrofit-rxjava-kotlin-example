package com.mxdl.retrofit.java.service;

import com.mxdl.retrofit.java.entity.Token;
import com.mxdl.retrofit.java.entity.User;
import io.reactivex.Observable;
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
public interface CommonRxService {
    @POST("/oauth/token")
    Observable<Token> getToken(@Header("Authorization") String authorization, @Query("grant_type") String grant_type, @Query("username") String username, @Query("password") String password);

    @POST("/users/user")
    Observable<User> createUser(@Body User user);
}
