package com.didimstory.mangulmangul.MyService

import android.os.Bundle
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


        webview.webViewClient = WebViewClient() // 클릭시 새창 안뜨게



        webview.settings.setJavaScriptEnabled(true) // 웹페이지 자바스클비트 허용 여부

        webview.settings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부

        webview.settings.setJavaScriptCanOpenWindowsAutomatically(false) // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부

        webview.settings.setLoadWithOverviewMode(true) // 메타태그 허용 여부

        webview.settings.setUseWideViewPort(true) // 화면 사이즈 맞추기 허용 여부

        webview.settings.setSupportZoom(false) // 화면 줌 허용 여부

        webview.settings.setBuiltInZoomControls(false) // 화면 확대 축소 허용 여부

        webview.settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN) // 컨텐츠 사이즈 맞추기

        webview.settings.setCacheMode(WebSettings.LOAD_NO_CACHE) // 브라우저 캐시 허용 여부

        webview.settings.setDomStorageEnabled(true) // 로컬저장소 허용 여부





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
                    response.body()?.contents

                webview.loadData(response.body()?.contents.toString(),"text/html","UTF-8")
                    webview.loadDataWithBaseURL(null,response.body()?.contents.toString(),"text/html","UTF-8",null)


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