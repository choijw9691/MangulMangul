package com.didimstory.mangulmangul.Purchase


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.apiResultItem
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.webviewAPI
import kotlinx.android.synthetic.main.activity_purchase.*
import kr.co.bootpay.Bootpay
import kr.co.bootpay.BootpayAnalytics
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG
import kr.co.bootpay.enums.UX
import kr.co.bootpay.listener.*
import kr.co.bootpay.model.BootExtra
import kr.co.bootpay.model.BootUser


class purchaseActivity : AppCompatActivity() {
    var purchaseList:ArrayList<Buy>? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var purchaseAdapter: purchaseAdapter
    private var dataList = arrayListOf<Buy>()
    val apiResult= apiResultItem()
    private val stuck = 10
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

        BootpayAnalytics.init(this, "604b709cd8c1bd002bf4c16e");
        payment.setOnClickListener(View.OnClickListener {


         startActivity(Intent(this,pgFragment::class.java))

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