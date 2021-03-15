package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.listfairyBuy
import com.didimstory.mangulmangul.Login.LoginActivity
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentEditMyInfoBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import kotlinx.android.synthetic.main.fragment_edit_my_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditMyInfoFragment : Fragment() {

    private lateinit var callback: OnBackPressedCallback
var binding:FragmentEditMyInfoBinding?=null
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

      binding=  FragmentEditMyInfoBinding.inflate(inflater, container, false)

        val view = binding?.root
        binding?.backbtn?.setOnClickListener(View.OnClickListener {
            var fragmentManager=parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()


        })


binding?.editbtn?.setOnClickListener(View.OnClickListener {
    Client.retrofitService.myInfoUpdate(PreferenceManager.getLong(context,"PrefIDIndex"),binding?.nickname?.text.toString().toString(),binding?.addr1?.text.toString(),binding?.addr2?.text.toString(),binding?.phone?.text.toString())
        .enqueue(object :
            Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                Log.d("editinfo","editinfo")

                if (response.body()!!){


                    var fragmentManager=parentFragmentManager
                    fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()


                }
            }

        })
})

binding?.exit?.setOnClickListener(View.OnClickListener {

    Client.retrofitService.myInfoExit(PreferenceManager.getLong(context,"PrefIDIndex"))
        .enqueue(object :
            Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {


                //여기 삭제할때 아이디 비번도 삭제

                PreferenceManager.removeKey(
                    context,
                    "PrefIDIndex"
                )
                var intent=Intent(context,LoginActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        })

})






        return view
    }

    companion object {
        fun newInstance(): EditMyInfoFragment? {
            var fragment: EditMyInfoFragment = EditMyInfoFragment()
            return fragment
        }
    }

}