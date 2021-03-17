package com.didimstory.mangulmangul.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EmailCheck(
    @SerializedName("userId")
    var userId: String
)

data class listfairyBuy(
    @SerializedName("artKitList")
    var list : List<fairyBuy>
)



data class listfairyHome(
    @SerializedName("fairyTaleList")
    var list : List<fairyHome>
)
data class listfameDetailHome(
    @SerializedName("fairyTaleList")
    var list : List<fameDetailHome>
)
data class fairyHome(
    @SerializedName("engFairyTaleIdx")
    var engFairyTaleIdx: Long
,
    @SerializedName("ytUrl")
    var ytUrl: String
    ,
    @SerializedName("title")
    var title: String

    ,
    @SerializedName("likeStatus")
    var  likestatus: Boolean

)

data class fameDetailHome(
    @SerializedName("engFairyTaleIdx")
    var engFairyTaleIdx: Long
    ,
    @SerializedName("ytUrl")
    var ytUrl: String
    ,
    @SerializedName("title")
    var title: String

)


data class fairyBuy(
    @SerializedName("artKitIdx")
    var artKitIdx: Int
    ,
  /*  @SerializedName("fileRealName")
    var fileRealName: String
    ,*/
    @SerializedName("name")
    var name: String
    ,
    @SerializedName("price")
    var  price: Int,

    @SerializedName("imageUri")
    var  imageUri: String

)

data class boastHome(
    @SerializedName("artKitIdx")
    var artKitIdx: Long
    ,
    /*  @SerializedName("fileRealName")
      var fileRealName: String
      ,*/
    @SerializedName("name")
    var name: String

    ,
    @SerializedName("price")
    var  price: Int,

    @SerializedName("imageUri")
    var  imageUri: String

)

data class purchaseList(
    @SerializedName("purchaseList")
var list : List<buyListItem>

)

data class buyListItem(
    @SerializedName("deliveryStatus")
    var deliveryStatus: String
    ,
    /*  @SerializedName("fileRealName")
      var fileRealName: String
      ,*/
    @SerializedName("createdAt")
    var createdAt: String
    ,
    @SerializedName("artKitList")
    var artKitList: List<ArtKit>,
    @SerializedName("deliveryCompany")
var deliveryCompany: String
,
    @SerializedName("deliveryNumber")
    var deliveryNumber: String


)

data class ArtKit(
    @SerializedName("artKitIdx")
    var artKitIdx: Int
    ,
    @SerializedName("fileRealName")
    var fileRealName: String

    ,
    @SerializedName("name")
    var  name: String,

    @SerializedName("count")
    var  count: Int,

    @SerializedName("price")
    var  price: Int
)



data class boastListResult(
    @SerializedName("boastList")
    var list : List<boastList>
)

data class boastList(
    @SerializedName("boastIdx")
    var boastIdx: Int
    ,
    @SerializedName("fileRealName")
    var fileRealName: String
    ,
    @SerializedName("title")
    var  title: String

)

data class noticeList(
    @SerializedName("noticeIdx")
    var noticeIdx: Int
    ,
    @SerializedName("title")
    var title: String
    ,
    @SerializedName("createdAt")
    var  createdAt: String

)
data class noticeListResult(
    @SerializedName("noticeList")
    var list : List<noticeList>

)



data class noticeDetail(

    @SerializedName("contents")
    var contents: String

)

data class inquiryList(

    @SerializedName("inquiryIdx")
    var inquiryIdx: Int
    ,
    @SerializedName("title")
    var title: String
    ,

    @SerializedName("inquiryStatus")
    var inquiryStatus: String
    ,
    @SerializedName("createdAt")
    var  createdAt: String

)



data class inquiryDetail(

    @SerializedName("inquiryIdx")
    var inquiryIdx: Int
    ,
    @SerializedName("title")
    var title: String
    ,
    @SerializedName("contents")
    var contents: String
    ,

    @SerializedName("inquiryStatus")
    var inquiryStatus: String
    ,
    @SerializedName("createdAt")
    var  createdAt: String
 ,
    @SerializedName("answerContents")
var  answerContents: String


)

data class inquiryListResult(
    @SerializedName("inquiryList")
    var list : List<inquiryList>

)
data class boastrList(

    @SerializedName("fileRealName")
    var fileRealName: String
    ,
    @SerializedName("likeStatus")
    var likeStatus: Boolean
    ,
    @SerializedName("title")
    var title: String
    ,
    @SerializedName("contents")
    var contents: String
,
    @SerializedName("deleted")
    var deleted: Boolean,    @SerializedName("boastIdx")
    var boastIdx: Int,
    @SerializedName("nickname")
    var nickname: String

)

data class boastrListResult(

    @SerializedName("boastList")
    var list : List<boastrList>

)

data class boastDetail(

    @SerializedName("boastIdx")
    var boastIdx: Int
    ,
    @SerializedName("fileRealName")
    var fileRealName: String
    ,
    @SerializedName("likeStatus")
    var likeStatus: Boolean
    ,
    @SerializedName("nickname")
    var nickname: String
    ,
    @SerializedName("title")
    var title: String
    ,
    @SerializedName("contents")
var contents : String


)data class boastDetailResult(

    @SerializedName("boastList")
    var list : List<boastDetail>

)