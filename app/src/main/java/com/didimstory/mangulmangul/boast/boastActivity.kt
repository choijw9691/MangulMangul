package com.didimstory.mangulmangul.boast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastDetailResult
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
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


    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastRecycleAdapter: boastDetailAdapter
    private var dataList = arrayListOf<boastDetailRecycleItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoastBinding.inflate(layoutInflater)

        var fragmentManager = supportFragmentManager
        var transaction = fragmentManager.beginTransaction()
        /*     transaction.replace(R.id.boastContainer, BoastDetailFragment())
             transaction.commit()*/



        if (intent.getStringExtra("data.url") != null) {
            var intentCheck = intent.getStringExtra("data.url").toString()
            var nickname = intent.getStringExtra("nickname").toString()
            var content = intent.getStringExtra("content").toString()
            var fragment: BoastDetailFragment? = BoastDetailFragment()
            var bundle: Bundle = Bundle()
            bundle.putString("intentCheckurl", intentCheck)
            bundle.putString("nickname", nickname)
            bundle.putString("content", content)
            fragment!!.arguments = bundle
            Log.d("intentCheckurl", intentCheck)
            transaction.replace(R.id.boastContainer, fragment).commit()

        }

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
                                list?.get(i)?.fileRealName,
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
                                        thumnail: String?,
                                        nickname: String?,
                                        content: String?
                                    ) {
                                        var transaction = fragmentManager.beginTransaction()
                                        var fragment: BoastDetailFragment? = BoastDetailFragment()
                                        var bundle: Bundle = Bundle()
                                        bundle.putString("thumnail", thumnail)
                                        bundle.putString("nickname", nickname)
                                        bundle.putString("content", content)
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


    }