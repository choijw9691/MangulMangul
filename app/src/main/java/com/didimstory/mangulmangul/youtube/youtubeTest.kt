package com.didimstory.mangulmangul.youtube

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.ActivityYoutubeTestBinding
import com.didimstory.mangulmangul.databinding.FragmentFairytaleBinding
import com.didimstory.mangulmangul.fairy.*
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_youtube_test.*
import kotlinx.android.synthetic.main.activity_youtube_test.view.*


class youtubeTest : YouTubeBaseActivity() {
    var videoId: String? = null


    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyDetailAdapter
    private lateinit var buyAdapter: fairybuyAdapter
    private var dataList = arrayListOf<YoutubeItem>()
    private var buydataList = arrayListOf<fairybuyItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoId = intent.getStringExtra("data.url")
        val url = com.didimstory.mangulmangul.fairy.videoId//유튜브 썸네일 불러오는 방법
        Log.d("youtuberesult", videoId.toString())
        setContentView(R.layout.activity_youtube_test)
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



//임시 하드코딩
        dataList.add(
            YoutubeItem(
                url, "고래와 상어1"
            )
        )

        dataList.add(
            YoutubeItem(
                url, "고래와 상어2"
            )
        )
        dataList.add(
            YoutubeItem(
                url, "고래와 상어3"
            )
        )
        dataList.add(
            YoutubeItem(
                url, "고래와 상어4"
            )
        )
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        fairyAdapter = fairyDetailAdapter(this)

        recyclerView1.layoutManager = mLayoutManager

        fairyAdapter.dataList = dataList

        recyclerView1.adapter = fairyAdapter




        //임시 하드코딩

        buydataList.add(
            fairybuyItem(
                url, "키트","300원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","300원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "키트","300원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","300원"
            )
        )
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        buyAdapter = fairybuyAdapter(this)

        buyrecycler.layoutManager = mLayoutManager

        buyAdapter.dataList = buydataList

        buyrecycler.adapter = buyAdapter



    }
}
//영상자동재생
//youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener { override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) { if (!wasRestored) { player.cueVideo(videoId) } player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener { override fun onAdStarted() {} override fun onLoading() {} override fun onVideoStarted() {} override fun onVideoEnded() {} override fun onError(p0: YouTubePlayer.ErrorReason) {} override fun onLoaded(videoId: String) { player.play() } }) } })

