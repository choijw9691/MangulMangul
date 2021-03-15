package com.didimstory.mangulmangul.MyService

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentQuestDetailBinding
import kotlinx.android.synthetic.main.activity_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)




       insert1.setOnClickListener(View.OnClickListener {
           Client.retrofitService.insertInquiry(
               PreferenceManager.getLong(this,"PrefIDIndex"),title1.text.toString(),content.text.toString()
           ).enqueue(object :
               Callback<Boolean> {
               override fun onFailure(call: Call<Boolean>, t: Throwable) {

               }

               override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                   if (response.body()!!.equals(true)){

                       Toast.makeText(applicationContext, "문의하기가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                       //   finish()
                   }
               }


           })

       })









    }
}