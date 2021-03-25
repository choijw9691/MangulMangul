package com.didimstory.mangulmangul.Entity

import java.io.Serializable

data class Buy(val artKitIdx:Int,val url:ArrayList<String>, val title:String,val price:String,var count:Int):Serializable {

}