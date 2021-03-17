package com.didimstory.mangulmangul.`object`

import android.content.Context
import android.util.Log
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.PreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface  likeobject{



    open fun likeclick(context: Context,engFairyTaleIdx:Long){


        Client.retrofitService.updateLike(
            PreferenceManager.getLong(
                context,
                "PrefIDIndex"
            ),engFairyTaleIdx
        ).enqueue(object :
                Callback<Boolean> {

            var d:Boolean=false

            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>)  {

    response.body()!!


            }

        })

    }

}