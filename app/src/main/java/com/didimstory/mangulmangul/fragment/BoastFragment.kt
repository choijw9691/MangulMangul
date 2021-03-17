package com.didimstory.mangulmangul.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.Entity.boastrList
import com.didimstory.mangulmangul.Entity.boastrListResult
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.BoastChildFragment
import com.didimstory.mangulmangul.boast.boastActivity
import com.didimstory.mangulmangul.boast.boastRecycleAdapter
import com.didimstory.mangulmangul.databinding.FragmentBoastBinding
import com.didimstory.mangulmangul.fairy.fairyRecycleAdapter
import com.didimstory.mangulmangul.youtube.YoutubeItem
import kotlinx.android.synthetic.main.fragment_boast.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoastFragment : Fragment(), MainActivity.OnBackPressedListener{

    val TAG_CHILD = "TAG_CHILD"
    val KEY_NUMBER = "KEY_NUMBER"
    private val mNumber = 0
    private var binding: FragmentBoastBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter:boastRecycleAdapter
    private var dataList = arrayListOf<boastRecycleItemData>()
    val videoId = "F-KjYNmsi0U"









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("check123","check6")





    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("check123","check7")

        // Inflate the layout for this fragment
        binding = FragmentBoastBinding.inflate(inflater, container, false)
        val view = binding?.root






        val url = videoId//유튜브 썸네일 불러오는 방법


   /*     dataList.add(
            boastRecycleItemData(
                url, "고래와 상어1","1"
            )
        )*/


        Client.retrofitService.boastList(PreferenceManager.getLong(context,"PrefIDIndex"))
            .enqueue(object :
                Callback<boastrListResult> {
                override fun onFailure(call: Call<boastrListResult>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<boastrListResult>,
                    response: Response<boastrListResult>
                ) {        Log.d("check123",response.body()?.list.toString())
                    when(response!!.code()){

                        200->
                        {
                    var list = response.body()?.list
                            for(i in 0 until (list!!.size)){
                                //  Log.d("listresult",list?.get(i)!!.ytUrl.toString())
                                dataList.add(
                                    boastRecycleItemData(
                                        list?.get(i)?.fileRealName,  list?.get(i)?.likeStatus, list?.get(i)?.title, list?.get(i)?.contents,list?.get(i)?.deleted,list?.get(i)?.boastIdx,list?.get(i)?.nickname.toString()
                                    )
                                )


                            }


                            mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            boastAdapter =
                                boastRecycleAdapter(context)

                            binding!!.recyclerView.apply {
                                this.layoutManager =
                                    mLayoutManager
                                this.adapter = boastAdapter



                            }

                            boastAdapter.dataList =
                                dataList





                        }

                    }
                }


            })






        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {






        binding?.boastButton?.setOnClickListener(View.OnClickListener {

            Log.d("onCreateView1111","check5")
            var childFragment:FragmentManager=childFragmentManager
                childFragment.beginTransaction()
                    .replace(R.id.boastContainer, BoastChildFragment())
                    .addToBackStack(null).commit()

            (activity as MainActivity).setOnBackPressedListener(this)

        })
    }

    companion object {
        fun newInstance(): BoastFragment? {
            var fragment:BoastFragment= BoastFragment()
            return fragment
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("check123","check1")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("check123","check2")
    }

    override fun onPause() {
        super.onPause()
        Log.d("check123","check3")
    }

    override fun onStop() {
        super.onStop()
        Log.d("check123","check4")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("check123","check5")
        (activity as MainActivity).setOnBackPressedListener(null)
   binding = null
    }

    override fun onBackPressed() {

        Log.d("onBackPressed","check5")
        if(childFragmentManager.backStackEntryCount>0){childFragmentManager.popBackStackImmediate()

        var parentFragment: FragmentManager =parentFragmentManager
        parentFragment.beginTransaction()
            .replace(R.id.boastContainer, BoastFragment())
           .commit()
        }
    }

    override fun onResume() {
        super.onResume()

        boastRecycleAdapter(context).notifyDataSetChanged()

    }


}