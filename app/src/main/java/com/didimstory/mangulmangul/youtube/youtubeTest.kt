package com.didimstory.mangulmangul.youtube

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.fairybuyItem
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.MyPage.MyPageHomeFragment
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.Purchase.purchaseActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.fairy.*
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_youtube_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class youtubeTest : YouTubeBaseActivity() {
    var videoId: String? = null
    var likeStatus: Boolean? = null
    var engFairyTaleIdx1: Long? = null
    var title: String? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyDetailAdapter
    var mediaPlayer : MediaPlayer = MediaPlayer()
    private var dataList = arrayListOf<YoutubeItem>()


    override fun onStart() {
        super.onStart()
overridePendingTransition(0, 0);

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_youtube_test)

        mediaPlayer= MediaPlayer.create(applicationContext,R.raw.bogle)
        mediaPlayer.isLooping=true
        mediaPlayer.start()

        videoId = intent.getStringExtra("data.url")
        engFairyTaleIdx1 = intent.getLongExtra("engFairyTaleIdx", 0).toLong()
        likeStatus = intent.getBooleanExtra("likeStatus", false)
        title = intent.getStringExtra("title")
        val url = com.didimstory.mangulmangul.fragment.videoId//유튜브 썸네일 불러오는 방법
        Log.d("youtuberesult", videoId.toString())

        mLayoutManager = LinearLayoutManager(

            applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView2.layoutManager = mLayoutManager

        fairyAdapter = fairyDetailAdapter(applicationContext, 0,object : fairyDetailAdapter.updateFairyListener{
            override fun add(engFairyTaleIdx: Long) {
                super.add(engFairyTaleIdx)

finish()
                Log.d("data.ytUrl1234",engFairyTaleIdx.toString())
            }
        })




       fairyText.setText(title)



    backbtn?.setOnClickListener(View.OnClickListener {
onBackPressed()


        })


        heart.setOnClickListener(View.OnClickListener {

            if (applicationContext != null) {


                if(likeStatus==true){

              heart.pauseAnimation()
                heart.progress=0f

                }else{

                  heart.playAnimation()
                   heart.loop(false);

                }

                Client.retrofitService.updateLike(
                    PreferenceManager.getLong(
                        applicationContext,
                        "PrefIDIndex"
                    ),engFairyTaleIdx1!!.toLong()
                ).enqueue(object :
                    Callback<Boolean> {


                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>)  {

                        likeStatus=response.body()!!


                    }

                })



            }

        })

        if (likeStatus == true) {

            heart.progress=1f

        } else {

            heart.progress=0f
        }

        gotoBtn.setOnClickListener(View.OnClickListener {

            var intent = Intent(applicationContext, fairyPopup::class.java)

//ActivityOptions.makeSceneTransitionAnimation(PopUpActivity()).toBundle()
            intent.putExtra("videoId", videoId)
            intent.putExtra("engFairyTaleIdx", engFairyTaleIdx1!!)
            startActivity(intent)

        })


        val youtubeView =
            findViewById<YouTubePlayerView>(R.id.youtubeView)
        youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener {




            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                player: YouTubePlayer,
                wasRestored: Boolean
            ) {
                if (!wasRestored) {
                    player.cueVideo(videoId)

                }


        player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
            override fun onAdStarted() {
                Log.d("result4561","11")
                if(mediaPlayer.isPlaying){

                    mediaPlayer.stop()
                }
            }

            override fun onLoading() {
                Log.d("result4561","22")
            }

            override fun onVideoStarted() {
                Log.d("result4561","33")
                if(mediaPlayer.isPlaying){

                    mediaPlayer.stop()
                }
            }

            override fun onLoaded(p0: String?) {
                Log.d("result4561","44")
            }

            override fun onVideoEnded() {
                Log.d("result4561","55")
            }

            override fun onError(p0: YouTubePlayer.ErrorReason?) {
                Log.e("result4561","66")
            }


        })


            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {


            }
        })


        Log.d("listresult1234",engFairyTaleIdx1.toString())


        Client.retrofitService.fairyDetail(
            PreferenceManager.getLong(
                applicationContext,
                "PrefIDIndex"
            ), engFairyTaleIdx1!!
        )
            .enqueue(object :
                Callback<listfairyHome> {
                override fun onFailure(call: Call<listfairyHome>, t: Throwable) {
                    Log.d("listresult1234",t.toString())
                }

                override fun onResponse(
                    call: Call<listfairyHome>,
                    response: Response<listfairyHome>
                ) {
                    when (response!!.code()) {

                        200 -> {

                            var list = response.body()?.list
                            Log.d("listresult9989", response.body().toString())
                            for (i in 0 until (response.body()?.list!!.size)) {


                                dataList.add(
                                    YoutubeItem(
                                        list?.get(i)!!.engFairyTaleIdx,
                                        list?.get(i)!!.ytUrl,
                                        list?.get(i)!!.title,
                                        list?.get(i)!!.likestatus
                                    )
                                )


                            }
                            /*     engFairyTaleIdx1 =  response.body()?.engFairyTaleIdx
                                 videoId=response.body()?.ytUrl

                                 title= response.body()?.title
                                 fairyText.setText(title)
                                 likeStatus=  response.body()?.likestatus
                                 Log.d("리스폰첵11",title.toString())*/

                            fairyAdapter.dataList = dataList
                            recyclerView2.adapter = fairyAdapter


                        }

                    }
                }


            })

//임시 하드코딩
        /*     dataList.add(
                 YoutubeItem(
                     url, "고래와 상어1"
                 )
             )*/


        //임시 하드코딩
/*
        buydataList.add(
            fairybuyItem
                url, "키트", "100원"
            )
        )
*/

    }

    override fun onBackPressed() {

        super.onBackPressed()

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}
//영상자동재생
//youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener { override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) { if (!wasRestored) { player.cueVideo(videoId) } player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener { override fun onAdStarted() {} override fun onLoading() {} override fun onVideoStarted() {} override fun onVideoEnded() {} override fun onError(p0: YouTubePlayer.ErrorReason) {} override fun onLoaded(videoId: String) { player.play() } }) } })

