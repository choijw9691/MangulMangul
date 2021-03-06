package com.didimstory.mangulmangul.famous

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.fairybuyItem
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.Entity.listfameDetailHome
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.Purchase.purchaseActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.fairy.*
import com.didimstory.mangulmangul.youtube.YoutubeItem
import com.didimstory.mangulmangul.youtube.YoutubeItemDetail
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_fairy_popup.*
import kotlinx.android.synthetic.main.activity_youtube_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class youtubeFamous : YouTubeBaseActivity(){


    var mediaPlayer : MediaPlayer = MediaPlayer()

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyDetailAdapter
    private lateinit var buyAdapter: fairybuyAdapter
    private var dataList = arrayListOf<YoutubeItem>()
    private var buydataList = arrayListOf<fairybuyItem>()
    var videoId: String? = null
    var likeStatus: Boolean? = null
    var engFairyTaleIdx: Long? = null
    var title: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoId = intent.getStringExtra("data.url")

        videoId = intent.getStringExtra("data.url")
        engFairyTaleIdx = intent.getLongExtra("engFairyTaleIdx", 0).toLong()
        likeStatus = intent.getBooleanExtra("likeStatus", false)
        title = intent.getStringExtra("title")
        val url = com.didimstory.mangulmangul.fragment.videoId//유튜브 썸네일 불러오는 방법

        setContentView(R.layout.activity_youtube_test)


        mediaPlayer= MediaPlayer.create(applicationContext,R.raw.bogle)
        mediaPlayer.isLooping=true
        mediaPlayer.start()
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
                    ),engFairyTaleIdx!!.toLong()
                ).enqueue(object :
                    Callback<Boolean> {


                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>)  {
                        Log.d("youtuberesult5", "videoId.toString()")
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


        backbtn?.setOnClickListener(View.OnClickListener {
            onBackPressed()


        })

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
                        Log.d("result4561","66")
                    }


                })




            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {


            }
        })

        gotoBtn.setOnClickListener(View.OnClickListener {

            var intent=Intent(applicationContext,fairyPopup::class.java)

//ActivityOptions.makeSceneTransitionAnimation(PopUpActivity()).toBundle()
            intent.putExtra("videoId",videoId)
            intent.putExtra("engFairyTaleIdx",engFairyTaleIdx!!)
            startActivity(intent)

        })
        Log.d("listresult",PreferenceManager.getLong(applicationContext,"PrefIDIndex").toString()+"//////"+intent.getLongExtra("engFairyTaleIdx",0).toString())

        Client.retrofitService.fameDetail(PreferenceManager.getLong(applicationContext,"PrefIDIndex"), intent.getLongExtra("engFairyTaleIdx",0))
            .enqueue(object :
                Callback<listfameDetailHome> {
                override fun onFailure(call: Call<listfameDetailHome>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<listfameDetailHome>,
                    response: Response<listfameDetailHome>
                ) {
                    when(response!!.code()){

                        200->
                        {
                            Log.d("listresult123",response.body()?.list.toString())
                                                var list = response.body()?.list
                                                for(i in 0 until (response.body()?.list!!.size)){

                                                    dataList.add(
                                                        YoutubeItem(
                                                            list?.get(i)!!.engFairyTaleIdx, list?.get(i)!!.ytUrl, list?.get(i)!!.title,false
                                                        )
                                                    )


                                                }



                                           //여기 고쳐!!!!!!!!!!!!!!!!!!


                                                recyclerView2.layoutManager = mLayoutManager

                                                fairyAdapter.dataList = dataList

                                                recyclerView2.adapter = fairyAdapter




                            //임시 하드코딩

                            /*       buydataList.add(
                                       fairybuyItem(
                                           url, "키트", "100원"
                                       )
                                   )*/

/*
                            mLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                            buyAdapter = fairybuyAdapter(applicationContext,object:fairybuyAdapter.buyTextListener{
                                override fun buyTotal(data: String?) {
                                    //      buyText.setText(data)
                                }

                                override fun buyList(purchase: ArrayList<Buy>) {

                                    val intent= Intent(applicationContext,purchaseActivity::class.java)
                                    intent.putExtra("purchaseList",purchase)
                                    startActivity(intent)

                                }


                            })

                            buyrecycler.layoutManager = mLayoutManager

                            buyAdapter.dataList = buydataList

                            buyrecycler.adapter = buyAdapter

                            buyBtn.setOnClickListener(View.OnClickListener {

                                buyAdapter.maxplus()

                            })*/

                        }





                    }
                }


            })







//임시 하드코딩
/*        dataList.add(
            YoutubeItem(
                url, "고래와 상어1"
            )
        )*/




    }

    //영상자동재생
//youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener { override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) { if (!wasRestored) { player.cueVideo(videoId) } player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener { override fun onAdStarted() {} override fun onLoading() {} override fun onVideoStarted() {} override fun onVideoEnded() {} override fun onError(p0: YouTubePlayer.ErrorReason) {} override fun onLoaded(videoId: String) { player.play() } }) } })
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}