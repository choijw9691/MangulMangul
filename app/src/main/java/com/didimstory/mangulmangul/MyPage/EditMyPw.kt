package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentEditMyPwBinding
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditMyPw : Fragment() {

    private lateinit var callback: OnBackPressedCallback
var binding:FragmentEditMyPwBinding?=null
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
       binding= FragmentEditMyPwBinding.inflate(inflater, container, false)
        binding?.newpw?.isEnabled=false
        binding?.newpwcheck?.isEnabled=false

        binding?.backbtn?.setOnClickListener(View.OnClickListener {
            var fragmentManager=parentFragmentManager
            fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()

        })

        binding?.checkpw?.setOnClickListener(View.OnClickListener {

            if(binding?.pw?.text.toString()==PreferenceManager.getString(context,"PrefPW")){
                binding?.newpw?.isEnabled=true
                binding?.newpwcheck?.isEnabled=true
            }

        })




        binding?.newpwcheck?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding?.newpwcheck?.text.toString() != null){

                    if (binding?.newpwcheck?.text.toString().equals(binding?.newpw?.text.toString())) {
                 binding?.changePw?.isEnabled=true

                    }
                }
                else Toast.makeText(context, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })

/*
        Client.retrofitService.updatePW(PreferenceManager.getLong(context,"PrefIDIndex"),binding?.nickname?.text.toString().toString(),binding?.addr1?.text.toString(),binding?.addr2?.text.toString(),binding?.phone?.text.toString())
            .enqueue(object :
                Callback<Boolean> {})

*/

binding?.changePw?.setOnClickListener(View.OnClickListener {
if(binding?.changePw?.isEnabled!! == true){

    Client.retrofitService.updatePW(PreferenceManager.getLong(context,"PrefIDIndex"),PreferenceManager.getString(context,"PrefPW"),binding?.newpw?.text.toString())
        .enqueue(object :
            Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {

            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.body()?.equals(true)!!){

                    Toast.makeText(context, "비밀번호가 수정되었습니다.", Toast.LENGTH_SHORT).show()
                    var fragmentManager=parentFragmentManager
                    fragmentManager.beginTransaction().replace(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack("21").commit()

                    PreferenceManager.removeKey(
                        context,
                        "PrefPW"
                    )
                    PreferenceManager.setString(
                        context,
                        "PrefPW",binding?.newpw?.text.toString()
                    )
                }
              else  Toast.makeText(context, "비밀번호 수정 실패.", Toast.LENGTH_SHORT).show()
            }
        })
}

})



        return binding?.root
    }

    companion object {
        fun newInstance(): EditMyPw? {
            var fragment: EditMyPw = EditMyPw()
            return fragment
        }
    }
}