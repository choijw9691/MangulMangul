package com.didimstory.mangulmangul.Login

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.Entity.EmailCheck
import com.didimstory.mangulmangul.Entity.apiResultItem
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import com.didimstory.mangulmangul.webviewAPI

import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    val apiResult = apiResultItem()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        next_btn.isClickable = false
        next_btn.setBackgroundColor(Color.WHITE)




        backbtn.setOnClickListener(View.OnClickListener {

            onBackPressed()
        })

        findViewById<Button>(R.id.sign_btn).setOnClickListener {

            Client.retrofitService.logUp(userName.text.toString(),userRoleMno.text.toString(),password.text.toString(),nickname.text.toString(),address1.text.toString(),address2.text.toString(),email.text.toString()).enqueue(object :
                Callback<Long> {
                override fun onFailure(call: Call<Long>, t: Throwable) {

                }

                override fun onResponse(call: Call<Long>, response: Response<Long>) {

                    when (response.body()!!.toLong()) {


                        0.toLong() -> {
                            Toast.makeText(
                                applicationContext,
                                "회원가입에 실패하였습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()

                        }
                        else -> {
                            Toast.makeText(applicationContext, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                            if (PreferenceManager.getString(applicationContext,"PrefID")!=""){
                                PreferenceManager.removeKey(applicationContext,"PrefID")
                                PreferenceManager.setString(applicationContext,"PrefID",userRoleMno.text.toString())
                                PreferenceManager.removeKey(applicationContext,"PrefIDIndex")
                                PreferenceManager.setLong(applicationContext,"PrefIDIndex",response.body()!!.toLong())
                                PreferenceManager.removeKey(applicationContext,"PrefPW")
                                PreferenceManager.setString(applicationContext,"PrefPW",password.text.toString())
                            }else{
                                PreferenceManager.setString(applicationContext,"PrefID",userRoleMno.text.toString())
                                PreferenceManager.removeKey(applicationContext,"PrefIDIndex")
                                PreferenceManager.setLong(applicationContext,"PrefIDIndex",response.body()!!.toLong() )
                                        PreferenceManager.setString(applicationContext,"PrefPW",password.text.toString())


                            }
                            startActivity(Intent(applicationContext,MainActivity::class.java))
                            finish()
                           this@SignUpActivity.setResult(Activity.RESULT_OK,intent)
                        }
                    }
                }


            })
        }


        pw_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (password.text.toString() != null){

                    if (password.text.toString().equals(pw_check.text.toString())) {
next_btn.setBackgroundResource(R.drawable.redbutton)
                        next_btn.visibility=View.VISIBLE
                        next_btn.setOnClickListener(View.OnClickListener {
                            lin1.visibility = View.GONE
                            lin2.visibility = View.VISIBLE
                        })
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


        })

        check_adr.setOnClickListener(View.OnClickListener {

            intent = Intent(this, webviewAPI::class.java)
            startActivityForResult(intent, 100)

        })




        findViewById<Button>(R.id.check_id).setOnClickListener {

            Log.d("signCheck", "버튼클릭")
            Client.retrofitService.emailCheck(userRoleMno.text.toString()).enqueue(object :
                Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {

                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {


                    when (response.body()) {

                        true -> {
                            Toast.makeText(applicationContext, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show()
                            password.isEnabled=true
                            pw_check.isEnabled=true
                        }
                        false -> {
                            Toast.makeText(
                                applicationContext,
                                "다른 아이디를 사용해주세요.",
                                Toast.LENGTH_SHORT
                            ).show()

                            userRoleMno.text.clear()
                        }
                    }


                }


            })
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d("chchch", apiResult.apiResult)
            when (requestCode) {
                100 -> address1.setText(data?.getStringExtra("result"))
            }
        }
    }


}