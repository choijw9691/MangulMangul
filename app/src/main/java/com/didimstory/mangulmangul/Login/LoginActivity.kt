package com.didimstory.mangulmangul.Login

import android.app.Activity
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
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity() : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       if(PreferenceManager.getLong(applicationContext,"PrefIDIndex") != (-1).toLong()){
Log.d("로그인?",PreferenceManager.getLong(applicationContext,"PrefIDIndex").toString())
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }
        val login_btn: Button = findViewById(R.id.login_btn)
        var id_tv: TextView = findViewById(R.id.id_tv)
        var pw_tv: TextView = findViewById(R.id.pw_tv)
        var signup_go: TextView = findViewById(R.id.signup_go)
        login_btn.setOnClickListener {
            Client.retrofitService.logIn(id_tv.text.toString(), pw_tv.text.toString())
                .enqueue(object :
                    Callback<Long> {
                    override fun onFailure(call: Call<Long>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Long>, response: Response<Long>) {
                        when (response!!.code()) {
                            200 -> {

                                when (response.body()!!.toLong()) {

                                    0.toLong() -> {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "로그인 실패 : 아이디나 비번이 올바르지 않습니다",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    else -> {
                                        if (PreferenceManager.getLong(
                                                applicationContext,
                                                "PrefIDIndex"
                                            ) != (-1).toLong()
                                        ) {
                                            PreferenceManager.removeKey(
                                                applicationContext,
                                                "PrefIDIndex"
                                            )
                                            PreferenceManager.setLong(
                                                applicationContext,
                                                "PrefIDIndex",
                                                response.body()!!.toLong()
                                            )


                                        } else {
                                            PreferenceManager.setLong(
                                                applicationContext,
                                                "PrefIDIndex",
                                                response.body()!!.toLong()
                                            )
                                        }

                                        Toast.makeText(
                                            this@LoginActivity,
                                            "로그인에 성공하였습니다.",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@LoginActivity,
                                                MainActivity::class.java
                                            )
                                        )

                                        finish()
                                    }

                                }

                            }
                            // rest 형식이 다르면 나옴
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


                })
        }

        signup_go.setOnClickListener {

            startActivityForResult(
                Intent(
                    this@LoginActivity,
                    SignUpActivity::class.java
                ),1002
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK){

            when(requestCode){
                1002-> this.finish()
            }
        }

    }
}