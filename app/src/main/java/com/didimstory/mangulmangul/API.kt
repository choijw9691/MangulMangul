package com.didimstory.mangul

import com.didimstory.mangulmangul.Entity.EmailCheck

import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("registerAction")  //로그인
    @FormUrlEncoded
    fun logIn(@Field("id") id: String, @Field("pw") pw: String): Call<Void>

    @POST("/user/account/regUser") //회원가임
    @FormUrlEncoded
    fun logUp(
        @Field("password") password: String,
        @Field("userRoleMno") userRoleMno: Int,
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("vendorCode ") vendorCode: String
    ): Call<Void>


    @POST("user/account/checkId")//이메일중복확인
    @FormUrlEncoded
    fun emailCheck(@Field("userId") userID: String): Call<EmailCheck>


}