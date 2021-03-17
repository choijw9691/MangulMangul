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
                Log.d("intentCheckurl",intentCheckurl.toString())
                imageUrlList?.add(boastDetailItem(intentCheckurl.toString()))
/*                imageUrlList?.add(boastDetailItem("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg"))
                imageUrlList?.add(boastDetailItem("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg"))*/





       binding?.boastDetailImage?.adapter=ViewPagerAdapter(imageUrlList)

                binding?.boastDetailImage?.orientation=ViewPager2.ORIENTATION_HORIZONTAL




           //    binding?.nickname?.setText(extra?.getString("nickname"))

                binding?.a?.setText("제목 : "+extra?.getString("title"))

                binding?.boastContent?.setText(extra?.getString("content"))

                if (extra?.getBoolean("likeStatus")==true){

                    binding?.heart?.frame= binding?.heart?.maxFrame!!.toInt()
                    binding?.heart?.setOnClickListener(View.OnClickListener {
                        binding?.heart?.frame= binding?.heart?.minFrame!!.toInt()
                    })
                }
                else{

                    binding?.heart?.frame= binding?.heart?.minFrame!!.toInt()
                    binding?.heart?.setOnClickListener(View.OnClickListener {
                        binding?.heart?.playAnimation()
                        binding?.heart?.loop(false);
                    })
                }








            } else if (extra?.getString("fileRealName") != null)
            {

                var boastIdx = extra?.getInt("boastIdx")
                var fileRealName = extra?.getString("fileRealName")
                var likeStatus = extra?.getBoolean("likeStatus")
                var nickname = extra?.getString("nickname")
                var title = extra?.getString("title")
                var content = extra?.getString("content")




                binding?.a?.setText("제목 : "+title)
                binding?.boastContent?.setText(content)


                if (likeStatus==true){

                    binding?.heart?.frame= binding?.heart?.maxFrame!!.toInt()
                    binding?.heart?.setOnClickListener(View.OnClickListener {
                        binding?.heart?.frame= binding?.heart?.minFrame!!.toInt()
                    })
                }
                else{

                    binding?.heart?.frame= binding?.heart?.minFrame!!.toInt()
                    binding?.heart?.setOnClickListener(View.OnClickListener {
                        binding?.heart?.playAnimation()
                        binding?.heart?.loop(false);
                    })
                }





                imageUrlList?.add(boastDetailItem(fileRealName.toString()))
                imageUrlList?.add(boastDetailItem(fileRealName.toString()))
                imageUrlList?.add(boastDetailItem(fileRealName.toString()))


                binding?.boastDetailImage?.adapter=ViewPagerAdapter(imageUrlList)
                binding?.boastDetailImage?.orientation=ViewPager2.ORIENTATION_HORIZONTAL



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