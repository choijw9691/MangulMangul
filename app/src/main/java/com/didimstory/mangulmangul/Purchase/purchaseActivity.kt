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
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.apiResultItem
import com.didimstory.mangulmangul.Entity.listfairyBuy
import com.didimstory.mangulmangul.Entity.loginUserInfo
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.PurchaseItemBinding
import com.didimstory.mangulmangul.webviewAPI
import kotlinx.android.synthetic.main.activity_purchase.*
import kotlinx.android.synthetic.main.purchase_item.*
import kr.co.bootpay.Bootpay
import kr.co.bootpay.BootpayAnalytics
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG
import kr.co.bootpay.enums.UX
import kr.co.bootpay.listener.*
import kr.co.bootpay.model.BootExtra
import kr.co.bootpay.model.BootUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class purchaseActivity : AppCompatActivity() {
    var purchaseList: ArrayList<Buy>? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var purchaseAdapter: purchaseAdapter
    private var dataList = arrayListOf<Buy>()
    val apiResult = apiResultItem()
    private val stuck = 10
    var binding: PurchaseItemBinding? = null
    var formatter: DecimalFormat = DecimalFormat("###,###")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        var purchaseList = intent.getSerializableExtra("purchaseList") as ArrayList<Buy>?





        checkbox.setOnClickListener(View.OnClickListener {

            if (!checkbox.isChecked){
checkbox.isChecked=true

                Client.retrofitService.loginUserInfo(
                    PreferenceManager.getLong(
                        applicationContext,
                        "PrefIDIndex"
                    )
                ).enqueue(object :
                    Callback<loginUserInfo> {
                    override fun onFailure(call: Call<loginUserInfo>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<loginUserInfo>,
                        response: Response<loginUserInfo>
                    ) {
                        name.setText(response.body()?.userName.toString())
                        addressText.setText(response.body()?.addr1)
                        addr2.setText(response.body()?.addr2)
                        email.setText(response.body()?.email)


                    }
                })

            }
else {checkbox.isChecked=false
                name.setText("")
                addressText.setText("")
                addr2.setText("")
                email.setText("")


            }


        })



        purchase_back.setOnClickListener(View.OnClickListener {


            finish()

        })

        if (purchaseList != null) {
            dataList = purchaseList
        }


        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        purchaseAdapter = purchaseAdapter(this,object : purchaseAdapter.buyTextListener{
            override fun buyTotal() {


                var artKitList: ArrayList<Int>? = ArrayList<Int>()
                var countList: ArrayList<Int>? = ArrayList<Int>()

                var priceresult = 0
                var titleresult = "상품명이없음"
                var check: Int = 0
                for (i in 0 until dataList.size) {
                    priceresult += ((dataList[i].price).toInt() * (dataList[i].count))

                    check++
                    artKitList?.add(dataList[i].artKitIdx)
                    countList?.add(dataList[i].count)


                }
                if (check > 1) {

                    titleresult = dataList[0].title + " 외" + " " + (check - 1).toString() + "개"
                } else {

                    titleresult = dataList[0].title
                }
                totalgoods.setText(formatter.format((priceresult.toString()).toInt()) + "원")

                priceresult +=2500
                totalprice.setText(formatter.format((priceresult.toString()).toInt()) + "원")








            }


        })
        purchaseRecyclerView.layoutManager = mLayoutManager
        purchaseAdapter.dataList = dataList

        purchaseRecyclerView.adapter = purchaseAdapter


        address.setOnClickListener(View.OnClickListener {

            intent = Intent(this, webviewAPI::class.java)
            startActivityForResult(intent, 100)

        })

        next.setOnClickListener(View.OnClickListener {
            purchaseLinear1.visibility = View.GONE
            purchaseLinear2.visibility = View.VISIBLE
            leaa.visibility = View.VISIBLE


        })

        BootpayAnalytics.init(this, "604b709cd8c1bd002bf4c16e");
        payment.setOnClickListener(View.OnClickListener {


            var artKitList: ArrayList<Int>? = ArrayList<Int>()
            var countList: ArrayList<Int>? = ArrayList<Int>()

            var priceresult = 0
            var titleresult = "상품명이없음"
            var check: Int = 0
            for (i in 0 until dataList.size) {
                priceresult += ((dataList[i].price).toInt() * (dataList[i].count))

                check++
                artKitList?.add(dataList[i].artKitIdx)
                countList?.add(dataList[i].count)


            }
            if (check > 1) {

                titleresult = dataList[0].title + " 외" + " " + (check - 1).toString() + "개"
            } else {

                titleresult = dataList[0].title
            }

            priceresult +=2500
            var intent = Intent(this, pgFragment::class.java)
            intent.putExtra("titleresult", titleresult.toString())
            intent.putExtra("priceresult", priceresult)
            intent.putExtra("name", name.text.toString())
            intent.putExtra("addressText", addressText.text.toString())
            intent.putExtra("addr2", addr2.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putIntegerArrayListExtra("artKitList", artKitList)
            intent.putIntegerArrayListExtra("countList", countList)


            startActivity(intent)

        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("333", "11111")
        if (resultCode == Activity.RESULT_OK) {
            Log.d("333", data?.getStringExtra("result").toString())
            when (requestCode) {
                100 -> {
                    addressText.setText(data?.getStringExtra("result").toString())


                }
            }
        }
    }

}