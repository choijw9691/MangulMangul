package com.didimstory.mangulmangul.MyPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentMyPageHomeBinding
import com.didimstory.mangulmangul.fragment.MypageFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageHomeFragment : Fragment() {

private var binding:FragmentMyPageHomeBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     binding=FragmentMyPageHomeBinding.inflate(inflater, container, false)
        val view=binding?.root

        binding?.MyInfo?.setOnClickListener(View.OnClickListener {
Log.d("MyInfoClick","MyInfoClick")
            parentFragmentManager.beginTransaction().replace(R.id.MyPageContainer,EditMyInfoFragment()).addToBackStack("1").commit()


        })
        binding?.MyPassword?.setOnClickListener(View.OnClickListener {

            parentFragmentManager.beginTransaction().replace(R.id.MyPageContainer,EditMyPw()).addToBackStack("2").commit()

        })

        binding?.MyBuy?.setOnClickListener(View.OnClickListener {

            parentFragmentManager.beginTransaction().replace(R.id.MyPageContainer,EdiyMyBuy()).addToBackStack("3").commit()

        })
        binding?.MyPPT?.setOnClickListener(View.OnClickListener {

            parentFragmentManager.beginTransaction().replace(R.id.MyPageContainer,EdiyMyBuy()).addToBackStack("4").commit()

        })


        return view
    }

    companion object {
        fun newInstance(): MypageFragment? {
            var fragment: MypageFragment = MypageFragment()
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
   //     (activity as MainActivity).setOnBackPressedListener(null)
        Log.d("dedede","dedede11")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("dedede","dedede")

    }


}