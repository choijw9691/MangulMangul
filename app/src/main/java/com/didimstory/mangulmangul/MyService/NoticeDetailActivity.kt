package com.didimstory.mangulmangul.MyService

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_notice_detail.*


class NoticeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_detail)




        notice_datail_title.setText(intent.getStringExtra("title"))
        notice_datail_date.setText(intent.getStringExtra("date"))
        notice_datail_content.setText(intent.getStringExtra("content"))


        Glide.with(applicationContext)
            .load(intent.getStringExtra("image"))
            .centerInside()
            .override(1000,1000)
            .into(notice_datail_image)


    }
}