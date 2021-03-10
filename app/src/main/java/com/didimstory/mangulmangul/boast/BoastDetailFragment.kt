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
import com.didimstory.mangulmangul.Entity.boastDetailItem
import com.didimstory.mangulmangul.MyPage.EditMyPPT
import com.didimstory.mangulmangul.databinding.FragmentBoastBinding
import com.didimstory.mangulmangul.databinding.FragmentBoastDetailBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import java.util.ArrayList

class BoastDetailFragment : Fragment() {
    private var binding: FragmentBoastDetailBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
var imageUrlList: ArrayList<boastDetailItem>? = ArrayList<boastDetailItem>()
    private lateinit var pageradapter:ViewPagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoastDetailBinding.inflate(inflater, container, false)
        val view = binding?.root




        var extra = this.arguments
        if (extra != null) {
            extra = arguments
            if (extra?.getString("intentCheckurl") != null) {
                val intentCheckurl = extra?.getString("intentCheckurl")

                imageUrlList?.add(boastDetailItem("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg"))
                imageUrlList?.add(boastDetailItem("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg"))
                imageUrlList?.add(boastDetailItem("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg"))





       binding?.boastDetailImage?.adapter=ViewPagerAdapter(imageUrlList)

                binding?.boastDetailImage?.orientation=ViewPager2.ORIENTATION_HORIZONTAL




               binding?.nickname?.setText(extra?.getString("nickname"))
                binding?.boastContent?.setText(extra?.getString("content"))



            } else if ((extra?.getString("thumnail") != null) && (extra?.getString("nickname") != null) && (extra?.getString(
                    "content"
                ) != null)
            ) {

                var thumnail = extra?.getString("thumnail")
                var nickname = extra?.getString("nickname")
                var content = extra?.getString("content")

                imageUrlList?.add(boastDetailItem(thumnail.toString()))
                imageUrlList?.add(boastDetailItem(thumnail.toString()))
                imageUrlList?.add(boastDetailItem(thumnail.toString()))


                binding?.boastDetailImage?.adapter=ViewPagerAdapter(imageUrlList)
                binding?.boastDetailImage?.orientation=ViewPager2.ORIENTATION_HORIZONTAL

                binding?.nickname?.setText(nickname)
                binding?.boastContent?.setText(content)

            }


        }

        return view
    }

    companion object {
        fun newInstance(): BoastDetailFragment? {
            var fragment: BoastDetailFragment = BoastDetailFragment()
            return fragment
        }


    }



}