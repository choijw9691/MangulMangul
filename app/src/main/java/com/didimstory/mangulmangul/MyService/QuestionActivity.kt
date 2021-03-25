package com.didimstory.mangulmangul.MyService

import android.media.MediaPlayer
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

    var mediaPlayer : MediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        mediaPlayer= MediaPlayer.create(applicationContext,R.raw.bogle)
        mediaPlayer.isLooping=true
        mediaPlayer.start()

        insert1.setOnClickListener(View.OnClickListener {
            Log.d("asasd",
                PreferenceManager.getLong(this, "PrefIDIndex")
                    .toString() + title1.text.toString() + content.text.toString()
            )



            Client.retrofitService.insertInquiry(
                PreferenceManager.getLong(this,"PrefIDIndex"),title1.text.toString(),content.text.toString()
            ).enqueue(object :
                Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {

                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                    Toast.makeText(applicationContext, "문의가 등록되었습니다.", Toast.LENGTH_SHORT).show()

                    finish()
                }


            })





        })
          Log.d("asasd",PreferenceManager.getLong(this,"PrefIDIndex").toString()+title1.text.toString()+content.text.toString())



        backbtn.setOnClickListener(View.OnClickListener {

            finish()

        })






    }

    override fun onDestroy() {
        super.onDestroy()
        if(mediaPlayer.isPlaying){

            mediaPlayer.stop()
        }
    }
}