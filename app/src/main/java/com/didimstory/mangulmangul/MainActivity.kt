package com.didimstory.mangulmangul

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.didimstory.mangulmangul.fragment.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val PREFERENCE = "template.android.hyogeuns"



    private val FairytaleFragment by lazy { FairytaleFragment() }
    private val BoastFragment by lazy { BoastFragment() }
    private val HomeFragment by lazy { HomeFragment() }
    private val MypageFragment by lazy { MypageFragment() }
private val FamousFragment by lazy { com.didimstory.mangulmangul.famous.FamousFragment() }
    private val ServiceFragment by lazy { ServiceFragment() }
var listener:OnBackPressedListener?=null

    private val fragments: List<Fragment> =
        listOf(FairytaleFragment, BoastFragment, HomeFragment, MypageFragment, ServiceFragment,FamousFragment)
    private val pagerAdapter: MainViewPagerAdapter by lazy { MainViewPagerAdapter(this, fragments) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        var users = pref.getString("username", "")

        viewPager.setUserInputEnabled(false)

        initViewPager()
        initNavigationBar()





    }
    fun replaceFragment(num:Int) {

        when(num){
0->{
    Log.d("fairyButton","fairyButton")
    viewPager.currentItem=0
}
            5->{
                viewPager.currentItem=5

            }
        }

    }

    override fun onBackPressed() {

        if(listener!=null){
            listener?.onBackPressed()
            Log.d("childback","childback1")
        }
        else{
            super.onBackPressed()
            Log.d("childback","childback2")
        }

    }
/*    fun replaceFragment(fragment: Fragment) {

var fragmentManager:FragmentManager=supportFragmentManager
        var fragmentTransaction:FragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.viewPager)
    }*/

    /*
        private inner class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
            override fun getItemCount(): Int=5
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FairytaleFragment()
                    1 -> BoastFragment()
                    2 -> HomeFragment()
                    3 -> MypageFragment()
                    4 -> ServiceFragment()
                    else -> error("No Fragment")
                }
            }
        }
    */

    private fun initNavigationBar() {
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                val page = when (it.itemId) {
                    R.id.tab_fairy -> 0
                    R.id.tab_boast
                    -> 1
                    R.id.tab_home
                    -> 2
                    R.id.tab_mypage->3
                    R.id.tab_service->4
                    else -> 5
                }
                if (page != viewPager.currentItem) {
                    viewPager.currentItem = page
            }
                true
            }
            selectedItemId = R.id.tab_home
        }
    }

    private fun initViewPager() {
        viewPager.run {
            adapter = pagerAdapter
            registerOnPageChangeCallback (object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val navigation = when (position) {
                     0-> R.id.tab_fairy
                         1-> R.id.tab_boast

                        2->  R.id.tab_home

                        3-> R.id.tab_mypage
                        4->R.id.tab_service

                     else->return
                    }
                    if (bottomNavigationView.selectedItemId != navigation) {
                        bottomNavigationView.selectedItemId = navigation
                    }
                }
            })
        }
    }

    fun setOnBackPressedListener(listener: OnBackPressedListener?){
        this.listener=listener
    }

interface OnBackPressedListener{
    fun onBackPressed()

}

}





