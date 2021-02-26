package com.didimstory.mangul

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @POST("registerAction")  //로그인
    @FormUrlEncoded
    fun logIn(@Field("id") id : String, @Field("pw") pw : String) : Call<Void>

    @POST("/vendor/registerAction") //회원가임
    @FormUrlEncoded
    fun logUp(@Field("password") password : String, @Field("userRoleMno") userRoleMno : Int, @Field("email") email : String, @Field("name") name : String, @Field("phone") phone : String, @Field("vendorCode ") vendorCode  : String) :  Call<Void>
}