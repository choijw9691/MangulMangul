package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastListResult
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.BoastDetailFragment
import com.didimstory.mangulmangul.boast.boastDetailAdapter
import com.didimstory.mangulmangul.boast.boastDetailRecycleItem
import com.didimstory.mangulmangul.databinding.ActivityBoastBinding
import com.didimstory.mangulmangul.databinding.FragmentEditMyPPTBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.fragment.BoastFragment
import com.didimstory.mangulmangul.fragment.MypageFragment
import com.didimstory.mangulmangul.fragment.videoId
import com.didimstory.mangulmangul.youtube.YoutubeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
    private lateinit var fairyAdapter: fairyRecycleAdapter

    private var dataList = arrayListOf<YoutubeItem>()


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


        binding?.boastBack?.setOnClickListener(View.OnClickListener {

            //  (activity as MainActivity).setOnBackPressedListener(null)

            childFragmentManager.popBackStackImmediate()
            var parentFragment: FragmentManager =parentFragmentManager
            parentFragment.beginTransaction()
                .replace(R.id.MyPageContainer, MyPageHomeFragment())
                .commit()
        })


/*        var fragment: BoastDetailFragment? = BoastDetailFragment()
        var bundle: Bundle = Bundle()
        bundle.putString("intentCheckurl", videoId)
        bundle.putString("nickname", "nickname")
        bundle.putString("content", "content")
        fragment!!.arguments = bundle

        childFragmentManager.beginTransaction().replace(R.id.boastContainer, fragment).commit()*/

        Client.retrofitService.pptGet(
            PreferenceManager.getLong(context,"PrefIDIndex")
        )
            .enqueue(object :
                Callback<boastListResult> {
                override fun onFailure(call: Call<boastListResult>, t: Throwable) {
 Log.d("getPPT",t.toString())
                }

                override fun onResponse(
                    call: Call<boastListResult>,
                    response: Response<boastListResult>
                ) {
                    Log.d("getPPT",response.code().toString())
                    Log.d("getPPT",response.message().toString())
                    when (response!!.code()) {

                        200 -> {
                            var list = response.body()?.list
                            Log.d("getPPT",list.toString())
                            for (i in 0 until (response.body()?.list!!.size)) {

                                dataList.add(
                                    YoutubeItem(
                                        list?.get(i)!!.boastIdx.toLong(),
                                        list?.get(i)!!.fileRealName.get(0),
                                        list?.get(i)!!.title,
                                        false
                                    )
                                )


                            }


                            mLayoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            fairyAdapter =
                                fairyRecycleAdapter(context, 3)

                            binding!!.recyclerView.apply {
                                this.layoutManager =
                                    mLayoutManager
                                this.adapter = fairyAdapter


                            }

                            fairyAdapter.dataList =
                                dataList


                        }

                    }

                }
            })
     /*   dataList.add(
            boastDetailRecycleItem(
                url, "1", "1"
            )
        )*/


        return view
    }


}