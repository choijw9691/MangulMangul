package com.didimstory.mangulmangul.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EmailCheck(
    @SerializedName("userId")
    var userId: String
)

data class listfairyHome(
    @SerializedName("fairyTaleList")
    var list : List<fairyHome>
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
    @SerializedName(" likeStatus")
    var  likestatus: Boolean

)


