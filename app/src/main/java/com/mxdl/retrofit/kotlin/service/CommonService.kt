package com.mxdl.retrofit.kotlin.service

import com.mxdl.retrofit.kotlin.entity.Token
import com.mxdl.retrofit.kotlin.entity.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Description: <CommonService><br>
 * Author:      mxdl<br>
 * Date:        2020/3/27<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
interface CommonService {
    @POST("/oauth/token")
    fun getToken(
        @Header("Authorization") authorization: String,
        @Query("grant_type") grant_type: String,
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<Token>

    @POST("/users/user")
    fun createUser(@Body user: User): Observable<User>
}