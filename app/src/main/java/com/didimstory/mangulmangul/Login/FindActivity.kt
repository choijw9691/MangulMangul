package com.didimstory.mangulmangul.Login

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.fragment_find.view.*

class FindActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_find)




        when(intent.getIntExtra("find",0)){

            0-> supportFragmentManager.beginTransaction().add(R.id.container_find,FindIdFragment()).commit()
            1-> supportFragmentManager.beginTransaction().add(R.id.container_find,FindPwFragment()).commit()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}