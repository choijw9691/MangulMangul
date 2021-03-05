package com.didimstory.mangulmangul.Purchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.youtube.YoutubeItem


import kotlinx.android.synthetic.main.activity_purchase.*


class purchaseActivity : AppCompatActivity() {
    var purchaseList:ArrayList<Buy>? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var purchaseAdapter: purchaseAdapter
    private var dataList = arrayListOf<Buy>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        var purchaseList= intent.getSerializableExtra("purchaseList") as ArrayList<Buy>?

 Log.d("purchaseList", purchaseList?.get(0)?.url.toString())

        if (purchaseList != null) {
            dataList=purchaseList
        }


        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        purchaseAdapter = purchaseAdapter(this)
       purchaseRecyclerView.layoutManager = mLayoutManager
        purchaseAdapter.dataList = dataList

        purchaseRecyclerView.adapter = purchaseAdapter

    }


}