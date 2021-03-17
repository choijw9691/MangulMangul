package com.didimstory.mangulmangul.youtube

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.Entity.fairybuyItem
import com.didimstory.mangulmangul.Entity.listfairyHome
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
    var engFairyTaleIdx: Long? = null

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyDetailAdapter

    private var dataList = arrayListOf<YoutubeItem>()


    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoId = intent.getStringExtra("data.url")
        engFairyTaleIdx = intent.getLongExtra("engFairyTaleIdx", 0)
        likeStatus = intent.getBooleanExtra("likeStatus", false)
        val url = com.didimstory.mangulmangul.fragment.videoId//유튜브 썸네일 불러오는 방법
        Log.d("youtuberesult", videoId.toString())
        setContentView(R.layout.activity_youtube_test)



        if (likeStatus == true) {

            heart.frame = heart.maxFrame.toInt()
            heart.setOnClickListener(View.OnClickListener {
                heart.frame = heart.minFrame.toInt()
                likeStatus = false
            })
        } else {

            heart.frame = heart.minFrame.toInt()

            heart.setOnClickListener(View.OnClickListener {
                heart.playAnimation()
                heart.loop(false);

                likeStatus = true
            })
        }






        gotoBtn.setOnClickListener(View.OnClickListener {

            var intent = Intent(applicationContext, fairyPopup::class.java)

//ActivityOptions.makeSceneTransitionAnimation(PopUpActivity()).toBundle()
            intent.putExtra("videoId", videoId)
            intent.putExtra("engFairyTaleIdx", engFairyTaleIdx!!)
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
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {


            }
        })



        Client.retrofitService.fairyHome(
            PreferenceManager.getLong(
                applicationContext,
                "PrefIDIndex"
            )
        )
            .enqueue(object :
                Callback<listfairyHome> {
                override fun onFailure(call: Call<listfairyHome>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<listfairyHome>,
                    response: Response<listfairyHome>
                ) {
                    when (response!!.code()) {

                        200 -> {
                            var list = response.body()?.list
                            for (i in 0 until (response.body()?.list!!.size)) {
                                Log.d("listresult", list?.get(i)!!.ytUrl.toString())
                                dataList.add(
                                    YoutubeItem(
                                        list?.get(i)!!.engFairyTaleIdx,
                                        list?.get(i)!!.ytUrl,
                                        list?.get(i)!!.title,
                                        list?.get(i)!!.likestatus
                                    )
                                )


                            }


                            mLayoutManager = LinearLayoutManager(
                                applicationContext,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            fairyAdapter = fairyDetailAdapter(applicationContext, 0)

                            recyclerView1.layoutManager = mLayoutManager

                            fairyAdapter.dataList = dataList

                            recyclerView1.adapter = fairyAdapter


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


}
//영상자동재생
//youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener { override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) { if (!wasRestored) { player.cueVideo(videoId) } player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener { override fun onAdStarted() {} override fun onLoading() {} override fun onVideoStarted() {} override fun onVideoEnded() {} override fun onError(p0: YouTubePlayer.ErrorReason) {} override fun onLoaded(videoId: String) { player.play() } }) } })

