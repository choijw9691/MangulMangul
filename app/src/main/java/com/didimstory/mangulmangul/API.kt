package com.didimstory.mangul

import com.didimstory.mangulmangul.Entity.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface API {
    @POST("user/account/login")  //로그인
    @FormUrlEncoded
    fun logIn(@Field("userId") id: String, @Field("password") pw: String): Call<Long>

    @POST("user/account/regUser") //회원가임
    @FormUrlEncoded
    fun logUp(
        @Field("userName") userName: String,
        @Field("userId") userId: String,
        @Field("password") password: String,
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

    @POST("user/artKit/fairyTaleArtKitList")//상품리스트
    @FormUrlEncoded
    fun fairyBuyList(@Field("engFairyTaleIdx") engFairyTaleIdx: Long): Call<listfairyBuy>

    @POST("user/fairyTale/fameFairyTaleList")//인기동화
    @FormUrlEncoded
    fun fameHome(@Field("userIdx") userIdx: Long): Call<listfairyHome>

    @POST("user/fairyTale/fameFairyTaleDetail")//인기영어동화상세
    @FormUrlEncoded
    fun fameDetail(
        @Field("userIdx") userIdx: Long,
        @Field("engFairyTaleIdx") engFairyTaleIdx: Long
    ): Call<listfameDetailHome>


    @POST("/user/boast/boastList")//
    @FormUrlEncoded
    fun boastHome(@Field("userIdx") userIdx: Long): Call<listfairyHome>


    @POST("user/purchase/purchaseInsert")//구매하기
    @FormUrlEncoded
    fun boastBuy(
        @Field("userIdx") userIdx: Long,
        @Field("addr1") addr1: String,
        @Field("addr2") addr2: String,
        @Field("artKitList") artKitList: ArrayList<Int>?,
        @Field("countList") countList: ArrayList<Int>?
    ): Call<Void>

    @POST("user/mypage/updateUserInfo")//개인정보수정
    @FormUrlEncoded
    fun myInfoUpdate(
        @Field("userIdx") userIdx: Long,
        @Field("nickname") nickname: String,
        @Field("addr1") addr1: String,
        @Field("addr2") addr2: String,
        @Field("phone") phone: String

    ): Call<Boolean>


    @POST("user/mypage/withdrawUser")//탈퇴
    @FormUrlEncoded
    fun myInfoExit(
        @Field("userIdx") userIdx: Long

    ): Call<Boolean>


    @POST("user/mypage/updatePwd")//비밀번호수정
    @FormUrlEncoded
    fun updatePW(
        @Field("userIdx") userIdx: Long,
        @Field("password") password: String,
        @Field("newPassword") newPassword: String

    ): Call<Boolean>


    @POST("user/mypage/purchaseList")//구매내역
    @FormUrlEncoded
    fun buyList(
        @Field("userIdx") userIdx: Long

    ): Call<purchaseList>

    @POST("user/mypage/portfolio")//포트폴리오
    @FormUrlEncoded
    fun pptGet(
        @Field("userIdx") userIdx: Long

    ): Call<boastListResult>


    @POST("user/cs/noticeList")//공지사항목록
    @FormUrlEncoded
    fun noticeList(

        @Field("setData") setData: Boolean
    ): Call<noticeListResult>


    @POST("user/cs/noticeDetail")//공지사항상세
    @FormUrlEncoded
    fun noticeDetail(
        @Field("noticeIdx") noticeIdx: Int
    ): Call<noticeDetail>


    @POST("user/cs/inquiryList")//문의하기목록
    @FormUrlEncoded
    fun noticeList(
        @Field("userIdx") userIdx: Long
    ): Call<inquiryListResult>


    @POST("user/cs/inquiryDetail")//문의하기상세
    @FormUrlEncoded
    fun inquiryDetail(
        @Field("inquiryIdx") inquiryIdx: Int
    ): Call<inquiryDetail>


    @POST("user/cs/insertInquiry")//문의등록
    @FormUrlEncoded
    fun insertInquiry(
        @Field("userIdx") userIdx: Long,
        @Field("title") title: String,
        @Field("contents") contents: String
    ): Call<Boolean>



    @POST("user/boast/boastList")//자랑하기
    @FormUrlEncoded
    fun boastList(
        @Field("userIdx") userIdx: Long

    ): Call<boastrListResult>


    @POST("user/boast/boastDetail")//자랑하기상세
   @FormUrlEncoded
    fun boastDetail(
        @Field("boastIdx") boastIdx : Int,
        @Field("userIdx") userIdx: Long

    ): Call<boastDetailResult>



    @Multipart
    @POST("user/boast/insertBoast") //자랑하기 등록
    fun uploadImage(@Part("userIdx")userIdx:Long,@Part("contents")contents:String,@Part("title")title:String,@Part image:List<MultipartBody.Part>):Call<Void>



    @POST("user/boast/updateLike") //자랑하기 좋아요
    @FormUrlEncoded
    fun boastupdateLike(@Field("userIdx") userIdx: Long,@Field("boastIdx") boastId: Long):Call<Boolean>


    @POST("user/fairyTale/updateLike")
    @FormUrlEncoded
    fun updateLike(@Field("userIdx") userIdx: Long,@Field("engFairyTaleIdx") engFairyTaleIdx: Long):Call<Boolean>



    @POST("user/fairyTale/fameFairyTaleList")
    @FormUrlEncoded
    fun fameFairyTaleList(@Field("userIdx") userIdx: Long,@Field("titleLike") titleLike: String):Call<listfairyHome>



    @POST("user/account/loginUserInfo") //구매자와 동일
    @FormUrlEncoded
    fun loginUserInfo(@Field("userIdx") userIdx: Long):Call<loginUserInfo>


}
