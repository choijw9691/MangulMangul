package com.didimstory.mangulmangul.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.fairyHome
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.databinding.FragmentFairytaleBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.youtube.YoutubeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
    private var binding: FragmentFairytaleBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyRecycleAdapter

    var resultList:List<fairyHome>?=null

    private var dataList = arrayListOf<YoutubeItem>()
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
        Log.d("ddddss","ddddss")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFairytaleBinding.inflate(inflater, container, false)
        val view = binding?.root


        Client.retrofitService.fairyHome(PreferenceManager.getLong(context,"PrefIDIndex"))
                .enqueue(object :
                    Callback<listfairyHome> {
                    override fun onFailure(call: Call<listfairyHome>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(
                        call: Call<listfairyHome>,
                        response: Response<listfairyHome>
                    ) {
                        when(response!!.code()){

                            200->
                            {
                                var list = response.body()?.list
                                for(i in 0 until (response.body()?.list!!.size)){
Log.d("listresult",list?.get(i)!!.ytUrl.toString())
                                    dataList.add(
                                        YoutubeItem(
                                            list?.get(i)!!.engFairyTaleIdx,  list?.get(i)!!.ytUrl, list?.get(i)!!.title, list?.get(i)!!.likestatus
                                        )
                                    )


                                }




                                mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                                fairyAdapter =
                                    fairyRecycleAdapter(context,0)

                                binding!!.recyclerView.apply {
                                    this.layoutManager =
                                        mLayoutManager
                                    this.adapter = fairyAdapter
                                    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {



                                    })


                                }

                                fairyAdapter.dataList =
                                    dataList

                            }

                        }
                    }


                })












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