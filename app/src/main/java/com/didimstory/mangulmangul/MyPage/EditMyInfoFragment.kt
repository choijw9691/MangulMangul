package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentEditMyInfoBinding
import com.didimstory.mangulmangul.fragment.BoastFragment
import kotlinx.android.synthetic.main.fragment_edit_my_info.*


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



        return view
    }

    companion object {
        fun newInstance(): EditMyInfoFragment? {
            var fragment: EditMyInfoFragment = EditMyInfoFragment()
            return fragment
        }
    }

}