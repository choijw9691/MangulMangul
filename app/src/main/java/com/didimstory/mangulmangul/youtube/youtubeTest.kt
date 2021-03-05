package com.didimstory.mangulmangul.youtube

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.Entity.Buy
import com.didimstory.mangulmangul.EventBus.CheckBoxEvent
import com.didimstory.mangulmangul.Purchase.purchaseActivity
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
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class youtubeTest : YouTubeBaseActivity(){
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
                url, "키트","100원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","200원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "키트","300원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","400원"
            )
        )
        buydataList.add(
            fairybuyItem(
                url, "키트","500원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","600원"
            )
        )
        buydataList.add(
            fairybuyItem(
                url, "키트","700원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","800원"
            )
        )
        buydataList.add(
            fairybuyItem(
                url, "키트","900원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","1000원"
            )
        )
        buydataList.add(
            fairybuyItem(
                url, "키트","1100원"
            )
        )

        buydataList.add(
            fairybuyItem(
                url, "서적","1200원"
            )
        )
        mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        buyAdapter = fairybuyAdapter(this,object:fairybuyAdapter.buyTextListener{
            override fun buyTotal(data: String?) {
                buyText.setText(data)
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

        })

    }



}
//영상자동재생
//youtubeView.initialize("develop", object : YouTubePlayer.OnInitializedListener { override fun onInitializationSuccess( provider: YouTubePlayer.Provider, player: YouTubePlayer, wasRestored: Boolean ) { if (!wasRestored) { player.cueVideo(videoId) } player.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener { override fun onAdStarted() {} override fun onLoading() {} override fun onVideoStarted() {} override fun onVideoEnded() {} override fun onError(p0: YouTubePlayer.ErrorReason) {} override fun onLoaded(videoId: String) { player.play() } }) } })

