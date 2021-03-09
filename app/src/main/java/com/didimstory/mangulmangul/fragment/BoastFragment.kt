package com.didimstory.mangulmangul.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.boast.BoastChildFragment
import com.didimstory.mangulmangul.boast.boastActivity
import com.didimstory.mangulmangul.boast.boastRecycleAdapter
import com.didimstory.mangulmangul.databinding.FragmentBoastBinding
import kotlinx.android.synthetic.main.fragment_boast.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoastFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoastFragment : Fragment(), MainActivity.OnBackPressedListener{

    val TAG_CHILD = "TAG_CHILD"
    val KEY_NUMBER = "KEY_NUMBER"
    private val mNumber = 0
    private var binding: FragmentBoastBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter:boastRecycleAdapter
    private var dataList = arrayListOf<boastRecycleItemData>()
    val videoId = "F-KjYNmsi0U"









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("check123","check6")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("check123","check7")

        // Inflate the layout for this fragment
        binding = FragmentBoastBinding.inflate(inflater, container, false)
        val view = binding?.root






        val url = videoId//유튜브 썸네일 불러오는 방법


        dataList.add(
            boastRecycleItemData(
                url, "고래와 상어1","1"
            )
        )

        dataList.add(
            boastRecycleItemData(
                "Hq2yWd5wG_M", "고래와 상어2","2"
            )
        )
        dataList.add(
            boastRecycleItemData(
                url, "고래와 상어3"    ,"3"        )
        )
        dataList.add(
            boastRecycleItemData(
                url, "고래와 상어4","4"
            )
        )
        mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        boastAdapter =
            boastRecycleAdapter(context)

        binding!!.recyclerView.apply {
            this.layoutManager =
                mLayoutManager
            this.adapter = boastAdapter



        }

        boastAdapter.dataList =
            dataList






        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.boastButton?.setOnClickListener(View.OnClickListener {

            Log.d("onCreateView1111","check5")
            var childFragment:FragmentManager=childFragmentManager
                childFragment.beginTransaction()
                    .replace(R.id.boastContainer, BoastChildFragment())
                    .addToBackStack(null).commit()

            (activity as MainActivity).setOnBackPressedListener(this)

        })
    }

    companion object {
        fun newInstance(): BoastFragment? {
            var fragment:BoastFragment= BoastFragment()
            return fragment
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("check123","check1")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("check123","check2")
    }

    override fun onPause() {
        super.onPause()
        Log.d("check123","check3")
    }

    override fun onStop() {
        super.onStop()
        Log.d("check123","check4")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("check123","check5")
        (activity as MainActivity).setOnBackPressedListener(null)
    }

    override fun onBackPressed() {

        Log.d("onBackPressed","check5")
        if(childFragmentManager.backStackEntryCount>0){  childFragmentManager.popBackStackImmediate()

        var parentFragment: FragmentManager =parentFragmentManager
        parentFragment.beginTransaction()
            .replace(R.id.boastContainer, BoastFragment())
           .commit()
        }
    }

    override fun onResume() {
        super.onResume()

    }
}