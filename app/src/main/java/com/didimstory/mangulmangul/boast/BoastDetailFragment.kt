package com.didimstory.mangulmangul.boast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.databinding.FragmentBoastBinding
import com.didimstory.mangulmangul.databinding.FragmentBoastDetailBinding
import com.didimstory.mangulmangul.fragment.BoastFragment

class BoastDetailFragment : Fragment() {
    private var binding: FragmentBoastDetailBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager


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
                binding?.boastDetailImage?.let {
                    Glide.with(this)
                        .load("https://img.youtube.com/vi/$intentCheckurl/maxresdefault.jpg")
                        .centerInside()
                        .override(1000, 1000)
                        .into(it)
                    Log.d("intentCheckurl", "intentCheckurl1")
                }
               binding?.nickname?.setText(extra?.getString("nickname"))
                binding?.boastContent?.setText(extra?.getString("content"))
                extra?.getString("content")


            } else if ((extra?.getString("thumnail") != null) && (extra?.getString("nickname") != null) && (extra?.getString(
                    "content"
                ) != null)
            ) {

                var thumnail = extra?.getString("thumnail")
                var nickname = extra?.getString("nickname")
                var content = extra?.getString("content")
                binding?.boastDetailImage?.let {
                    Glide.with(this)
                        .load(thumnail)
                        .centerInside()
                        .override(1000, 1000)
                        .into(it)
                    Log.d("intentCheckurl", "intentCheckurl1")
                }
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