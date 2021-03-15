package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.buyListItem
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.Entity.purchaseList
import com.didimstory.mangulmangul.Entity.purchaseListItem
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentEdiyMyBuyBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.youtube.YoutubeItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EdiyMyBuy : Fragment() {

    private var dataList = arrayListOf<purchaseListItem>()
    private lateinit var callback: OnBackPressedCallback
var binding:FragmentEdiyMyBuyBinding?=null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: purchaseListAdapter


    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
           /*     var fragmentManager=parentFragmentManager
                fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()

            */
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentEdiyMyBuyBinding.inflate(inflater, container, false)
        binding?.backbtn?.setOnClickListener(View.OnClickListener {
            var fragmentManager=parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()


        })


        Client.retrofitService.buyList(PreferenceManager.getLong(context,"PrefIDIndex"))
            .enqueue(object :
                Callback<purchaseList> {
                override fun onFailure(call: Call<purchaseList>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<purchaseList>,
                    response: Response<purchaseList>
                ) {

                   var list = response.body()?.list
           for(i in 0 until (response.body()?.list!!.size)){


                    }
                    //서브


               //메인
                Log.d("listresult1212", list?.get(0)!!.artKitList.toString())
                    mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    fairyAdapter =
                        purchaseListAdapter(context,0)

                    binding!!.recyclerView.apply {
                        this.layoutManager =
                            mLayoutManager
                        this.adapter = fairyAdapter



                    }

                    fairyAdapter.dataList =
                        list

                }

            })


        return binding?.root
    }
    companion object {
        fun newInstance(): EdiyMyBuy? {
            var fragment: EdiyMyBuy = EdiyMyBuy()
            return fragment
        }
    }

}