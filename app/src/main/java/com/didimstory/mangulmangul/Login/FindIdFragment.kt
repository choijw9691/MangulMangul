package com.didimstory.mangulmangul.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.EmailCheck
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentFindIdBinding
import kotlinx.android.synthetic.main.fragment_find_id.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FindIdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindIdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
var binding:FragmentFindIdBinding?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentFindIdBinding.inflate(inflater, container, false)
val view=binding?.root

        binding?.backbtn?.setOnClickListener(View.OnClickListener {

            activity?.onBackPressed()

        })


        binding?.findIdBtn?.setOnClickListener {
            Client.retrofitService.findID(find_id_email.text.toString())
                .enqueue(object :
                    Callback<EmailCheck> {
                    override fun onFailure(call: Call<EmailCheck>, t: Throwable) {
                    }
                    override fun onResponse(
                        call: Call<EmailCheck>,
                        response: Response<EmailCheck>
                    ) {
                        Log.d("chchch",response.body().toString())
                      when (response!!.code()) {

                            200->{
                                Log.d("chchch","1")
                                when(response.body()?.userId?.toString()){
                                    "" -> {
                                        binding?.findIdLin1?.visibility=View.GONE
                                        binding?.findIdLin2?.visibility=View.VISIBLE

                                        binding?.findResultText?.text="아이디가 없습니다."
                                        binding?.findResultText?.currentTextColor?.let { it1 ->
                                            binding?.findResultText?.setTextColor(
                                                it1
                                            )

                                        }

                                        binding?.findResultBtn?.setOnClickListener(View.OnClickListener {
                                            activity?.onBackPressed()
                                            activity?.finish()
                                        })
                                    }
                                    else ->  {
                                        binding?.findIdLin1?.visibility=View.GONE
                                        binding?.findIdLin2?.visibility=View.VISIBLE

                                        binding?.findResultText?.text="아이디 : " + response.body()?.userId?.toString()
                                        binding?.findResultText?.currentTextColor?.let { it1 ->
                                            binding?.findResultText?.setTextColor(
                                                it1
                                            )
                                        }
                                        binding?.findResultBtn?.setOnClickListener(View.OnClickListener {
                                            activity?.onBackPressed()
                                            activity?.finish()
                                        })
                                    }
                                }

                            }

                        }

                    }


                })
        }




        return view
    }



}