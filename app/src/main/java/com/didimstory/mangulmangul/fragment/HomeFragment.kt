package com.didimstory.mangulmangul.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.famous.FamousFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() ,View.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: HomeFragment

    companion object {
        fun newInstance(): HomeFragment? {

            var fragment:HomeFragment= HomeFragment()
            return fragment

        }
    }

    override fun onResume() {
        super.onResume()




        val animation = AnimationUtils.loadAnimation(context,R.anim.leftright)
      popfairyButton.startAnimation(animation)

        val animation1 = AnimationUtils.loadAnimation(context,R.anim.leftrighttext)
        text11.startAnimation(animation1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

view.boastButton.setOnClickListener(this)
        view.popfairyButton.setOnClickListener(this)
        view.fairyButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {

        when(v?.getId()){


            R.id.fairyButton -> {

                FairytaleFragment.newInstance()?.let { (activity as MainActivity).replaceFragment(it) }}
         //   else ->  (activity as MainActivity).initViewPager().onpage
            R.id.popfairyButton ->{

               FamousFragment.newInstance()?.let { (activity as MainActivity).replaceFragment(it) }


            }

            R.id.boastButton->{

                BoastFragment.newInstance()?.let { (activity as MainActivity).replaceFragment(it) }

            }
        }


    }


}