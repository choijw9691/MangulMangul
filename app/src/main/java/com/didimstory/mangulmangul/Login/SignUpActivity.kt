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

        pw_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (password.text.toString().equals(pw_check.text.toString())) {
                    next_btn.isClickable = true
                    next_btn.setBackgroundColor(R.drawable.redbutton)
                    next_btn.setOnClickListener(View.OnClickListener {
                        lin1.visibility = View.GONE
                        lin2.visibility = View.VISIBLE
                    })
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
                Callback<EmailCheck> {
                override fun onFailure(call: Call<EmailCheck>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<EmailCheck>,
                    response: Response<EmailCheck>
                ) {


                    Toast.makeText(
                        applicationContext,
                        response.body()?.toString(),
                        Toast.LENGTH_SHORT
                    ).show()


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