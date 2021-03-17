package com.didimstory.mangulmangul.youtube

import android.graphics.drawable.Drawable

data class YoutubeItem (val engFairyTaleIdx: Long, val ytUrl: String, val title:String,
                        var likeStatus:Boolean)
data class YoutubeItemDetail (val engFairyTaleIdx: Long,val ytUrl: String,val title:String)