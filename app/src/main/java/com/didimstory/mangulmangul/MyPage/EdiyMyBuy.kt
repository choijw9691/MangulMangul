package com.didimstory.mangulmangul.MyPage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.didimstory.mangulmangul.R

class EdiyMyBuy : Fragment() {


    private lateinit var callback: OnBackPressedCallback

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
        return inflater.inflate(R.layout.fragment_ediy_my_buy, container, false)
    }


}