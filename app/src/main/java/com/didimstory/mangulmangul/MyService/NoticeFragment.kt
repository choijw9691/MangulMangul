package com.didimstory.mangulmangul.MyService

import android.app.Service
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastListResult
import com.didimstory.mangulmangul.Entity.noticeDetailItem
import com.didimstory.mangulmangul.Entity.noticeListResult
import com.didimstory.mangulmangul.MyPage.MyPageHomeFragment
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.BoastDetailFragment
import com.didimstory.mangulmangul.boast.boastDetailAdapter
import com.didimstory.mangulmangul.boast.boastDetailRecycleItem
import com.didimstory.mangulmangul.databinding.FragmentNoticeBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import com.didimstory.mangulmangul.fragment.videoId
import com.didimstory.mangulmangul.youtube.YoutubeItem
import kotlinx.android.synthetic.main.fragment_notice.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NoticeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NoticeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var callback: OnBackPressedCallback
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastRecycleAdapter: QuestionAdapter
    private var dataList = arrayListOf<noticeDetailItem>()
    var binding: FragmentNoticeBinding? = null
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.Servicecontainer, ServiceHomeFragment()).commit()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoticeBinding.inflate(inflater, container, false)
        val view = binding?.root
        val url = videoId//유튜브 썸네일 불러오는 방법



        Client.retrofitService.noticeList(
true
        )
            .enqueue(object :
                Callback<noticeListResult> {
                override fun onFailure(call: Call<noticeListResult>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<noticeListResult>,
                    response: Response<noticeListResult>
                ) {
                    var list = response.body()?.list
                    for (i in 0 until (response.body()?.list!!.size)) {

                        dataList.add(
                            noticeDetailItem(
                                list?.get(i)!!.noticeIdx,
                                list?.get(i)!!.title,
                                list?.get(i)!!.createdAt

                            )
                        )

                    }

                    mLayoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    boastRecycleAdapter =
                        QuestionAdapter(context)

                    binding?.noticeRecycler.apply {
                        this?.layoutManager =
                            mLayoutManager
                        this?.adapter = boastRecycleAdapter


                    }

                    boastRecycleAdapter.dataList = dataList


                }

            })


/*      dataList.add(
            noticeDetailItem(
               "공지사항제목", "안녕하세요", "2021.00.00","null"
            )
        )*/








        return view
    }

    companion object {

/*        fun newInstance(param1: String, param2: String) =
            NoticeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/

        fun newInstance(): NoticeFragment? {
            var fragment = NoticeFragment()
            return fragment
        }


    }
}