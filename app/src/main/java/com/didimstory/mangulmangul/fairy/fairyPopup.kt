package com.didimstory.mangulmangul.fairy

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.fairybuyItem
import com.didimstory.mangulmangul.Entity.listfairyBuy
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.Purchase.purchaseActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.youtube.YoutubeItem
import kotlinx.android.synthetic.main.activity_fairy_popup.*
import kotlinx.android.synthetic.main.activity_pop_up.*
import kotlinx.android.synthetic.main.activity_youtube_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fairyPopup  : Activity() {
    private var buydataList = arrayListOf<fairybuyItem>()
    private lateinit var buyAdapter: fairybuyAdapter
    var data:String?=null
    var engFairyTaleIdx:Long?=null
    private lateinit var mLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setGravity(Gravity.RIGHT)


        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            with(window)
            { requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                // set an slide transition
                enterTransition = Slide(Gravity.BOTTOM)
                exitTransition = Slide(Gravity.TOP) }
        }


        setContentView(R.layout.activity_fairy_popup)

        val display=this.baseContext.display
        val size= Point()
        display?.getRealSize(size)
        val width=size.x
        val height=size.y
        Log.d("sizecheck",width.toString()+"/////"+height.toString())
        window.attributes.width= width/2

        window.attributes.height= WindowManager.LayoutParams.MATCH_PARENT

        data=intent.getStringExtra("videoId").toString()
        engFairyTaleIdx=intent.getLongExtra("engFairyTaleIdx",0)

        Client.retrofitService.fairyBuyList(engFairyTaleIdx!!)
            .enqueue(object :
                Callback<listfairyBuy> {
                override fun onFailure(call: Call<listfairyBuy>, t: Throwable) {
                    Log.d("listresult114",t.toString())
                }

                override fun onResponse(
                    call: Call<listfairyBuy>,
                    response: Response<listfairyBuy>
                ) {
                    when (response!!.code()) {

                        200 -> {
                            var list = response.body()?.list

                            for (i in 0 until (response.body()?.list!!.size)) {
                                Log.d("listresult113",list?.get(i)!!.imageUri.toString())
                                buydataList.add(
                                    fairybuyItem(
                                        list?.get(i)!!.artKitIdx,
                                        list?.get(i)!!.imageUri,
                                        list?.get(i)!!.name,
                                        list?.get(i)!!.price.toString()
                                    )
                                )



                            }


                        }

                    }

                    mLayoutManager = LinearLayoutManager(
                        applicationContext,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    buyAdapter = fairybuyAdapter(applicationContext,
                        object : fairybuyAdapter.buyTextListener {
                            override fun buyTotal(data: String?) {
                                //  buyText.setText(data)
                            }

                            override fun buyList(purchase: ArrayList<Buy>) {
if(purchase.size>0){

    val intent = Intent(
        applicationContext,
        purchaseActivity::class.java
    )
    finish()
    Log.d("fffffffffffffff11",purchase.get(0).title.toString())
    intent.putExtra("purchaseList", purchase)
    startActivity(intent)

}
              else{

    Toast.makeText(applicationContext, "도구를 선택해주세요", Toast.LENGTH_SHORT).show()

}

                            }


                        })

                    buyrecycler.layoutManager = mLayoutManager

                    buyAdapter.dataList = buydataList

                    buyrecycler.adapter = buyAdapter

                    buyBtn.setOnClickListener(View.OnClickListener {
                        Log.d("fffffffffffffff","2")
                        buyAdapter.maxplus()

                    })
                }
            }





    )






}





    fun mOnClose(view: View) {
        val intent = Intent()
        intent.putExtra("result", txtText.text.toString())
        setResult(RESULT_OK, intent)
        finish()



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
        if(event?.action== MotionEvent.ACTION_OUTSIDE){
            return false
        }
        return true
    }

    override fun onBackPressed() {
        return
    }
}