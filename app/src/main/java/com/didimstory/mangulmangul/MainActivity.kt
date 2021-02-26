package com.didimstory.mangulmangul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    val PREFERENCE = "template.android.hyogeuns"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
        var users = pref.getString("username", "")





    }
}