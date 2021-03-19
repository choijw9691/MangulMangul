package com.didimstory.mangulmangul.famous

import android.content.Intent
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
    var videoId: String? = null


    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyDetailAdapter
    private lateinit var buyAdapter: fairybuyAdapter
    private var dataList = arrayListOf<YoutubeItem>()
    private var buydataList = arrayListOf<fairybuyItem>()

    var engFairyTaleIdx: Long? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoId = intent.getStringExtra("data.url")

        engFairyTaleIdx = intent.getLongExtra("engFairyTaleIdx",0)
        val url = com.didimstory.mangulmangul.fragment.videoId//유튜브 썸네일 불러오는 방법
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

                                                mLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

                                           //여기 고쳐!!!!!!!!!!!!!!!!!!

                                            //    fairyAdapter = fairyDetailAdapter(applicationContext,1)

                                                recyclerView1.layoutManager = mLayoutManager

                                                fairyAdapter.dataList = dataList

                                                recyclerView1.adapter = fairyAdapter




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

}