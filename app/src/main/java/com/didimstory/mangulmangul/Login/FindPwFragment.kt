package com.didimstory.mangulmangul.Login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.EmailCheck
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentFindIdBinding
import com.didimstory.mangulmangul.databinding.FragmentFindPwBinding
import kotlinx.android.synthetic.main.fragment_find_id.*
import kotlinx.android.synthetic.main.fragment_find_id.find_id_email
import kotlinx.android.synthetic.main.fragment_find_id.find_result_text
import kotlinx.android.synthetic.main.fragment_find_pw.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FindPwFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindPwFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentFindPwBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFindPwBinding.inflate(inflater, container, false)
        val view = binding?.root

        binding?.backbtn?.setOnClickListener(View.OnClickListener {

            activity?.onBackPressed()
        })

            binding?.findIdBtn?.setOnClickListener {
                Client.retrofitService.findPW(find_id_email.text.toString())
                    .enqueue(object :
                        Callback<Boolean> {
                        override fun onFailure(call: Call<Boolean>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                            Log.d("chchch",response.body().toString())
                            when (response!!.code()) {

                                200->{
                                    Log.d("chchch","1")
                                    when(response.body()){
                                        false -> {
                                            binding?.findIdLin1?.visibility=View.GONE
                                            binding?.findIdLin2?.visibility=View.VISIBLE
                                            binding?.findResultText?.visibility=View.VISIBLE


                                            binding?.findResultBtn?.setOnClickListener(View.OnClickListener {
                                                activity?.onBackPressed()
                                                activity?.finish()
                                            })
                                        }
                                        true ->  {
                                            binding?.findIdLin1?.visibility=View.GONE
                                            binding?.findIdLin2?.visibility=View.VISIBLE

                                            binding?.findResultText1?.visibility=View.VISIBLE

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