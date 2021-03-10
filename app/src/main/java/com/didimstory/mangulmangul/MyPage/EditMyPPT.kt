package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.transaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.BoastDetailFragment
import com.didimstory.mangulmangul.boast.boastDetailAdapter
import com.didimstory.mangulmangul.boast.boastDetailRecycleItem
import com.didimstory.mangulmangul.databinding.ActivityBoastBinding
import com.didimstory.mangulmangul.databinding.FragmentEditMyPPTBinding
import com.didimstory.mangulmangul.fragment.videoId


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditMyPPT.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditMyPPT : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastRecycleAdapter: boastDetailAdapter
    private var dataList = arrayListOf<boastDetailRecycleItem>()


    val thumnail by lazy { requireArguments().getString("thumnail") }
    val nickname by lazy { requireArguments().getString("nickname") }
    val content by lazy { requireArguments().getString("content") }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                var fragmentManager=parentFragmentManager
                fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val binding = FragmentEditMyPPTBinding.inflate(inflater, container, false)
       val view= binding.root



        val url = videoId//유튜브 썸네일 불러오는 방법



        var fragment: BoastDetailFragment? = BoastDetailFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("intentCheckurl", videoId)
        bundle.putString("nickname", "nickname")
        bundle.putString("content", "content")
        fragment!!.arguments = bundle

        childFragmentManager.beginTransaction().replace(R.id.boastContainer, fragment).commit()




        dataList.add(
            boastDetailRecycleItem(
                url, "1", "1"
            )
        )
        dataList.add(
            boastDetailRecycleItem(
                url, "2", "2"
            )
        )
        dataList.add(
            boastDetailRecycleItem(
                url, "3", "3"
            )
        )
        dataList.add(
            boastDetailRecycleItem(
                url, "4", "4"
            )
        )
        mLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        boastRecycleAdapter =
            boastDetailAdapter(context, object : boastDetailAdapter.BoastDetailListener {
                override fun detailListener(
                    thumnail: String?,
                    nickname: String?,
                    content: String?
                ) {
                    var transaction = childFragmentManager.beginTransaction()
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


        return view
    }


}