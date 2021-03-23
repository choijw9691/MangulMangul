package com.didimstory.mangulmangul.boast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastDetailItem
import com.didimstory.mangulmangul.Entity.boastDetailResult
import com.didimstory.mangulmangul.MyPage.EditMyPPT
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentBoastBinding
import com.didimstory.mangulmangul.databinding.FragmentBoastDetailBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import kotlinx.android.synthetic.main.activity_youtube_test.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class BoastDetailFragment : Fragment() {
    private var binding: FragmentBoastDetailBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
var imageUrlList: ArrayList<boastDetailItem>? = ArrayList<boastDetailItem>()
    private lateinit var pageradapter:ViewPagerAdapter
var likeStatus:Boolean=false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoastDetailBinding.inflate(inflater, container, false)
        val view = binding?.root




        var extra = arguments

        var boastIdx = extra?.getInt("boastIdx")


        Log.d("제발확인좀",boastIdx.toString())


        Client.retrofitService.boastDetail(
            boastIdx!!,
            PreferenceManager.getLong(context, "PrefIDIndex")
        )
            .enqueue(object :
                Callback<boastDetailResult> {
                override fun onFailure(call: Call<boastDetailResult>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<boastDetailResult>,
                    response: Response<boastDetailResult>

                ) {
                    likeStatus=response.body()!!.likeStatus
                    Log.d("좋아용상태",response.body()!!.likeStatus.toString())
                    if (likeStatus == true) {
                        binding?.heart?.progress=1f
                    } else {

                        binding?.heart?.progress=0f
                    }
                    binding?.a?.setText("제목 : "+ response.body()?.title)
                    binding?.boastContent?.setText(response.body()?.contents)

                        for(i in 0 until (response.body()?.fileRealName?.size!!)){

                            imageUrlList?.add(boastDetailItem(response.body()?.fileRealName!![i]))
                        }


                    binding?.boastDetailImage?.adapter=ViewPagerAdapter(imageUrlList)

                    binding?.boastDetailImage?.orientation=ViewPager2.ORIENTATION_HORIZONTAL








                }

            })














        binding?.heart?.setOnClickListener(View.OnClickListener {

            if (context != null) {


                if (likeStatus == true) {
                    Log.d("ss111113", likeStatus.toString())
                    // binding.heart.frame = binding.heart.minFrame.toInt()
                    binding?.heart?.pauseAnimation()
                    binding?.heart?.progress = 0f

                } else {
                    Log.d("ss111114", likeStatus.toString())
                    binding?.heart?.playAnimation()
                    binding?.heart?.loop(false);

                }




                Client.retrofitService.boastupdateLike(
                    PreferenceManager.getLong(
                        context,
                        "PrefIDIndex"
                    ),boastIdx!!.toLong()
                ).enqueue(object :
                    Callback<Boolean> {


                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                        //   Log.d("sslssl",response.body()!!.toString())
                        // likeStatus = response.body()!!


                    }

                })
            }})













        return view
    }

    companion object {
        fun newInstance(): BoastDetailFragment? {
            var fragment: BoastDetailFragment = BoastDetailFragment()
            return fragment
        }


    }



}