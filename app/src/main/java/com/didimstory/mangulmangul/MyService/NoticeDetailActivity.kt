package com.didimstory.mangulmangul.MyService

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.noticeDetail
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_notice_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class NoticeDetailActivity : AppCompatActivity() {
private var mWebSettings:WebSettings?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)

var file = File("efefwef")




        purchase_back1.setOnClickListener(View.OnClickListener {

            finish()

        })


        notice_datail_title.setText(intent.getStringExtra("title"))
        notice_datail_date.setText(intent.getStringExtra("date"))

        Client.retrofitService.noticeDetail(
            intent.getIntExtra("noticeIdx",0)
        )
            .enqueue(object :
                Callback<noticeDetail> {
                override fun onFailure(call: Call<noticeDetail>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<noticeDetail>,
                    response: Response<noticeDetail>
                ) {
                   notice_datail_content.setText(response.body()?.contents)



                }


            })





      /*  Glide.with(applicationContext)
            .load(intent.getStringExtra("image"))
            .centerInside()
            .override(1000,1000)
            .into(notice_datail_image)
*/

    }
}