package com.didimstory.mangulmangul.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.didimstory.mangulmangul.Entity.boastRecycleItemData
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
class BoastFragment : Fragment(), View.OnClickListener {


    private var binding: FragmentBoastBinding? = null
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var boastAdapter:boastRecycleAdapter
    private var dataList = arrayListOf<boastRecycleItemData>()
    val videoId = "F-KjYNmsi0U"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBoastBinding.inflate(inflater, container, false)
        val view = binding?.root


binding?.boastButton?.setOnClickListener(this)

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

    companion object {
        fun newInstance(): BoastFragment? {
            var fragment:BoastFragment= BoastFragment()
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        when(p0){
            boastButton ->  {

                val intent=Intent(this.context,boastActivity::class.java)
                startActivity(intent)
            }


        }

    }



}