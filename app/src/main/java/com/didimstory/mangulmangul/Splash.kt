package com.didimstory.mangulmangul

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangulmangul.Login.LoginActivity
import com.didimstory.mangulmangul.boast.boastActivity

class Splash: AppCompatActivity()  {

    var mediaPlayer : MediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)


        object : Thread() {
            override fun run() {
                try {
                    sleep(800)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
           mediaPlayer= MediaPlayer.create(applicationContext,R.raw.splashsound1)
                    mediaPlayer.start()
                }
            }
        }.start()


        object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                runOnUiThread {
                    val intent = Intent(applicationContext,LoginActivity::class.java)

                    startActivity(intent)
                   finish()
                }
            }
        }.start()

    }
}