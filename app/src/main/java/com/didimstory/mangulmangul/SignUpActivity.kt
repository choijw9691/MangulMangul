package com.didimstory.mangulmangul

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangul.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var password: TextView =findViewById(R.id.password)
        var userRoleMno: TextView =findViewById(R.id.userRoleMno)
        var email: TextView =findViewById(R.id.email)
        var name: TextView =findViewById(R.id.name)
        var phone: TextView =findViewById(R.id.phone)
        var vendorCode : TextView =findViewById(R.id.vendorCode )
        findViewById<Button>(R.id.sign_btn).setOnClickListener {
            Client.retrofitService.logUp(password.text.toString(), userRoleMno.text.toString().toInt(), email.text.toString(),name.text.toString(),phone.text.toString(),vendorCode .text.toString()).enqueue(object :
                Callback<Void> {
                override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                    when (response!!.code()) {
                        200 -> {
                            Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_LONG).show()
                            finish ()
                        }
                        405 -> Toast.makeText(this@SignUpActivity, "회원가입 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@SignUpActivity, "회원가입 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Void>?, t: Throwable?) {

                }


            })
        }
    }
}