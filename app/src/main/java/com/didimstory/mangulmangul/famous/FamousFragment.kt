package com.didimstory.mangulmangul.famous

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.databinding.FragmentFamousBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.fragment.FairytaleFragment
import com.didimstory.mangulmangul.fragment.videoId
import com.didimstory.mangulmangul.youtube.YoutubeItem


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var binding: FragmentFamousBinding? = null
private lateinit var mLayoutManager: LinearLayoutManager
private lateinit var fairyAdapter: fairyRecycleAdapter
private var dataList = arrayListOf<YoutubeItem>()


class FamousFragment : Fragment(){

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFamousBinding.inflate(inflater, container, false)
        val view = binding?.root




        val url = videoId//유튜브 썸네일 불러오는 방법


      
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        fairyAdapter =
            fairyRecycleAdapter(context,1)

        binding!!.recyclerView.apply {
            this.layoutManager =
                mLayoutManager
            this.adapter = fairyAdapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {



            })


        }

        fairyAdapter.dataList =
            dataList
/*      binding!!.titleText.text = fairyAdapter.dataList[0].title
        binding!!.descriptionText.text = String.format("%s ", fairyAdapter.dataList[0].description)*/

        return view
    }

    companion object {
        fun newInstance(): Int? {
            var fragment: FairytaleFragment =
                FairytaleFragment()
            Log.d("fairyfragment", fragment.toString())
            return 5
        }
    }





}