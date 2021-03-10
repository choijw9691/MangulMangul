package com.didimstory.mangulmangul.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.MyPage.*
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.databinding.FragmentFamousBinding
import com.didimstory.mangulmangul.databinding.FragmentMypageBinding
import kotlinx.android.synthetic.main.fragment_mypage.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var binding: FragmentMypageBinding? = null

/**
 * A simple [Fragment] subclass.
 * Use the [MypageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MypageFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Log.d("backpressnull","b3333333333")
   //   (activity as MainActivity).setOnBackPressedListener(null)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      binding= FragmentMypageBinding.inflate(inflater, container, false)
        val view = binding?.root


        var fragmentManager=childFragmentManager
        fragmentManager.beginTransaction().add(R.id.MyPageContainer,MyPageHomeFragment()).addToBackStack(null).commit()


        return view
    }
    companion object {
        fun newInstance(): MypageFragment? {
            var fragment:MypageFragment= MypageFragment()
            return fragment

        }
    }


}