package com.didimstory.mangulmangul.boast

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastDetailResult
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.MyPage.MyPageHomeFragment
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.ActivityBoastBinding
import com.didimstory.mangulmangul.fairy.fairybuyAdapter
import com.didimstory.mangulmangul.fragment.BoastFragment

import com.didimstory.mangulmangul.fragment.videoId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class boastActivity : AppCompatActivity() {
    var mediaPlayer : MediaPlayer = MediaPlayer()

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastRecycleAdapter: boastDetailAdapter
    private var dataList = arrayListOf<boastDetailRecycleItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoastBinding.inflate(layoutInflater)

        mediaPlayer= MediaPlayer.create(applicationContext,R.raw.bogle)
        mediaPlayer.isLooping=true
        mediaPlayer.start()


        binding?.backbtn?.setOnClickListener(View.OnClickListener {
onBackPressed()

        })

        /*     transaction.replace(R.id.boastContainer, BoastDetailFragment())
             transaction.commit()*/




        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()

            var fragment: BoastDetailFragment? = BoastDetailFragment()
            var bundle: Bundle = Bundle()

            fragment!!.arguments = bundle
        bundle.putInt("boastIdx",intent.getIntExtra("boastIdx", 0))
            transaction.replace(R.id.boastContainer, fragment).commit()



        val url = videoId//유튜브 썸네일 불러오는 방법






        Client.retrofitService.boastDetail(
            intent.getIntExtra("boastIdx", 0),
            PreferenceManager.getLong(applicationContext, "PrefIDIndex")
        )
            .enqueue(object :
                Callback<boastDetailResult> {
                override fun onFailure(call: Call<boastDetailResult>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<boastDetailResult>,
                    response: Response<boastDetailResult>
                ) {



                    var list = response.body()?.list
                    for (i in 0 until (list!!.size)) {
                        //  Log.d("listresult",list?.get(i)!!.ytUrl.toString())

                        dataList.add(
                            boastDetailRecycleItem(
                                list?.get(i)?.boastIdx,
                                list?.get(i)?.fileRealName.get(0),
                                list?.get(i)?.likeStatus,
                                list?.get(i)?.nickname,
                                list?.get(i)?.title,
                                list?.get(i)?.contents
                            )
                        )


                        mLayoutManager =
                            LinearLayoutManager(
                                applicationContext,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        boastRecycleAdapter =
                            boastDetailAdapter(
                                applicationContext,
                                object : boastDetailAdapter.BoastDetailListener {
                                    override fun detailListener(
                                        boastIdx: Int,
                                        fileRealName: String?,
                                        likeStatus: Boolean,
                                        nickname: String?,
                                        title: String?,
                                        content: String?
                                    ) {
                                        var transaction = fragmentManager.beginTransaction()
                                        var fragment: BoastDetailFragment? = BoastDetailFragment()
                                        var bundle: Bundle = Bundle()
                                        bundle.putInt("boastIdx",boastIdx)

                                        fragment!!.arguments = bundle
                                        transaction.replace(R.id.boastContainer, fragment).commit()
                                    }


                                })
                        binding!!.boastRecycler.apply {
                            this.layoutManager =
                                mLayoutManager
                            this.adapter = boastRecycleAdapter


                        }

                        boastRecycleAdapter.dataList = dataList
                    }


                }

                })


/*        dataList.add(
            boastDetailRecycleItem(
                url, "1", "1"
            )

        )*/




                setContentView(binding.root)
            }

    override fun onDestroy() {
        super.onDestroy()

        if(mediaPlayer.isPlaying){

            mediaPlayer.stop()
        }

    }
}