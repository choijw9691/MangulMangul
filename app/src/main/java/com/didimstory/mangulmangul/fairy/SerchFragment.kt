package com.didimstory.mangulmangul.fairy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.fairyHome
import com.didimstory.mangulmangul.Entity.listfairyHome
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentSerchBinding
import com.didimstory.mangulmangul.fragment.ServiceFragment
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
 * Use the [SerchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SerchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var binding: FragmentSerchBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var fairyAdapter: fairyRecycleAdapter
    var resultList:List<fairyHome>?=null
    private var dataList = arrayListOf<YoutubeItem>()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Log.d("ddddss","ddddss")
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
        binding=FragmentSerchBinding.inflate(inflater, container, false)


        binding?.fairyTitle?.setText(arguments?.getString("result"))
        var resultName=arguments?.getString("change")

        when(resultName){
            "fairypop" -> {
                Client.retrofitService.fairyHome(PreferenceManager.getLong(context,"PrefIDIndex"))
                    .enqueue(object :
                        Callback<listfairyHome> {
                        override fun onFailure(call: Call<listfairyHome>, t: Throwable) {

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

            }

        }




        binding?.serchBtn?.setOnClickListener(View.OnClickListener {

            var intent= Intent(context,PopUpActivity::class.java)

//ActivityOptions.makeSceneTransitionAnimation(PopUpActivity()).toBundle()
            intent.putExtra("data","fairypop")
            startActivityForResult(intent,1)

        })



        return binding?.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1){
            if(resultCode==-1){
                var result=data?.getStringExtra("result")
                var aa= ServiceFragment()
                var bundle=Bundle()
                bundle.putString("change","fairypop")
                bundle.putString("change","result")
                aa.arguments=bundle
                parentFragmentManager.beginTransaction().replace(R.id.viewPager,aa)


            }

        }
    }


    companion object {
        fun newInstance(): Int? {
            var fragment: SerchFragment = SerchFragment()
            return 6

        }
    }
}