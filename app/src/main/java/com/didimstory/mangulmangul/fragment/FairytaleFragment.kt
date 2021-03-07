package com.didimstory.mangulmangul.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangulmangul.databinding.FragmentFairytaleBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.youtube.YoutubeItem


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var binding: FragmentFairytaleBinding? = null
private lateinit var mLayoutManager: LinearLayoutManager
private lateinit var fairyAdapter: fairyRecycleAdapter
private var dataList = arrayListOf<YoutubeItem>()
val videoId = "F-KjYNmsi0U"
/**
 * A simple [Fragment] subclass.
 * Use the [FairytaleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FairytaleFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFairytaleBinding.inflate(inflater, container, false)
        val view = binding?.root




        val url = videoId//유튜브 썸네일 불러오는 방법


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
                url, "고래와 상어3"            )
        )
        dataList.add(
            YoutubeItem(
                url, "고래와 상어4"
            )
        )
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        fairyAdapter =
            fairyRecycleAdapter(context)

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
            return 0
        }
    }


}