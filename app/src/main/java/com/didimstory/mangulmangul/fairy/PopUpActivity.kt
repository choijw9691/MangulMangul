package com.didimstory.mangulmangul.fairy

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.util.Log
import android.view.*
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_pop_up.*


class PopUpActivity : Activity() {

    var data:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
window.setGravity(Gravity.TOP)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            with(window)
          { requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
                // set an slide transition
                enterTransition = Slide(Gravity.BOTTOM)
                exitTransition = Slide(Gravity.TOP) }
        }


        setContentView(R.layout.activity_pop_up)
        val display=applicationContext.display
        val size=Point()
        display?.getRealSize(size)
        val width=size.x
        val height=size.y
        Log.d("sizecheck",width.toString()+"/////"+height.toString())
        window.attributes.width=WindowManager.LayoutParams.MATCH_PARENT


       data=intent.getStringExtra("data").toString()




    }

    fun mOnClose(view: View) {
        val intent = Intent()
        intent.putExtra("result", txtText.text.toString())
        setResult(RESULT_OK, intent)
        finish()



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
        if(event?.action==MotionEvent.ACTION_OUTSIDE){
            return false
        }
        return true
    }

    override fun onBackPressed() {
      return
    }
}