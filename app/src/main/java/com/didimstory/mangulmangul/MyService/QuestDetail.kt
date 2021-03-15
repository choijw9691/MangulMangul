package com.didimstory.mangulmangul.MyService

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.inquiryDetail
import com.didimstory.mangulmangul.Entity.inquiryListResult
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentQuestDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestDetail : Fragment() {
/*    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding:FragmentQuestDetailBinding?=null
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

        binding=FragmentQuestDetailBinding.inflate(inflater, container, false)
binding?.insert?.setOnClickListener(View.OnClickListener {
    Log.d("clclcl","clclcl")
    Client.retrofitService.insertInquiry(
        PreferenceManager.getLong(context,"PrefIDIndex"),binding?.title?.text.toString(),binding?.content?.text.toString()
    )
        .enqueue(object :
            Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.body()!!.equals(true)){

                    Toast.makeText(context, "문의하기가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.Servicecontainer, ServiceHomeFragment()).commit()
                }
            }


        })




})



        return binding?.root
    }*/


}