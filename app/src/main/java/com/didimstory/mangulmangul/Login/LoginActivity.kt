package com.didimstory.mangulmangul.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.SharedPref
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity() : AppCompatActivity() {
    val PREFERENCE = "template.android.hyogeuns"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        SharedPref.openSharedPrep(this)
        val login_btn: Button = findViewById(R.id.login_btn)
        var id_tv: TextView = findViewById(R.id.id_tv)
        var pw_tv: TextView = findViewById(R.id.pw_tv)
        var signup_go: TextView = findViewById(R.id.signup_go)
        login_btn.setOnClickListener {
            Client.retrofitService.logIn(id_tv.text.toString(), pw_tv.text.toString())
                .enqueue(object :
                    Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        when (response!!.code()) {
                            200 -> {
                                val pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
                                val editor = pref.edit()
                                editor.putString("username", id_tv.text.toString())
                                editor.commit()
                                finish()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            }
                            405 -> Toast.makeText(
                                this@LoginActivity,
                                "로그인 실패 : 아이디나 비번이 올바르지 않습니다",
                                Toast.LENGTH_LONG
                            ).show()
                            500 -> Toast.makeText(
                                this@LoginActivity,
                                "로그인 실패 : 서버 오류",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {

                    }


                })
        }

        signup_go.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    SignUpActivity::class.java
                )
            )
        }
        var intent1 = Intent(this@LoginActivity, FindActivity::class.java)
        intent1.putExtra("find", 0)
        var intent2 = Intent(this@LoginActivity, FindActivity::class.java)
        intent2.putExtra("find", 1)
        find_id.setOnClickListener(View.OnClickListener {

            startActivity(intent1)
        })
        find_pw.setOnClickListener(View.OnClickListener { startActivity(intent2) })
    }
}