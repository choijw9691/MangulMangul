package com.didimstory.mangulmangul.Purchase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.apiResultItem
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.webviewAPI
import com.didimstory.mangulmangul.youtube.YoutubeItem


import kotlinx.android.synthetic.main.activity_purchase.*


class purchaseActivity : AppCompatActivity() {
    var purchaseList:ArrayList<Buy>? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var purchaseAdapter: purchaseAdapter
    private var dataList = arrayListOf<Buy>()
    val apiResult= apiResultItem()

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


        address.setOnClickListener(View.OnClickListener {

            intent=Intent(this,webviewAPI::class.java)
            startActivityForResult(intent, 100)

        })

        next.setOnClickListener(View.OnClickListener {
            purchaseLinear1.visibility=View.GONE
            purchaseLinear2.visibility=View.VISIBLE
            purchaseRecyclerView.visibility=View.VISIBLE


        })
        payment.setOnClickListener(View.OnClickListener {




        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("333","11111")
        if(resultCode==Activity.RESULT_OK){
            Log.d("333",data?.getStringExtra("result").toString())
            when(requestCode){
                100-> addressText.setText(data?.getStringExtra("result").toString())
            }
        }
    }

}