package com.didimstory.mangul

import com.didimstory.mangulmangul.Entity.EmailCheck
import com.didimstory.mangulmangul.Entity.fairyHome
import com.didimstory.mangulmangul.Entity.listfairyHome

import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("user/account/login")  //로그인
    @FormUrlEncoded
    fun logIn(@Field("userId") id: String, @Field("password") pw: String): Call<Long>

    @POST("user/account/regUser") //회원가임
    @FormUrlEncoded
    fun logUp(
        @Field("userName") userName: String,
        @Field("userId") userId: String,
        @Field("password") password:String,
        @Field("nickname") nickname: String,
        @Field("addr1") addr1: String,
        @Field("addr2") addr2: String,
        @Field("email") email: String
    ): Call<Long>


    @POST("user/account/checkId")//이메일중복확인
    @FormUrlEncoded
    fun emailCheck(@Field("userId") userID: String): Call<Boolean>


    @POST("user/account/findId")//아이디 찾기
    @FormUrlEncoded
    fun findID(@Field("email") userID: String): Call<EmailCheck>


    @POST("user/account/tempPw")//임시 비밀번호
    @FormUrlEncoded
    fun findPW(@Field("email") userID: String): Call<Boolean>


    @POST("user/fairyTale/fairyTaleList")//영어동화
    @FormUrlEncoded
    fun fairyHome(@Field("userIdx") userIdx: Long): Call<listfairyHome>


    @POST("user/fairyTale/fairyTaleDetail")//영어동화상세
    @FormUrlEncoded
    fun fairyDetail(@Field("userIdx") userIdx: Long,@Field("engFairyTaleIdx") engFairyTaleIdx:Long): Call<listfairyHome>

}