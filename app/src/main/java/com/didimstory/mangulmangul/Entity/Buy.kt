package com.didimstory.mangulmangul.Entity

import java.io.Serializable

data class Buy(val artKitIdx:Int,val url:String, val title:String,val price:Int,var count:Int):Serializable {

}