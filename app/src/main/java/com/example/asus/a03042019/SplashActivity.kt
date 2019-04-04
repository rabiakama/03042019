package com.example.asus.a03042019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val backgrond=object : Thread() {

            override fun run() = try {
                Thread.sleep(3000)
                val intent= Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
            }
            catch (e: Exception){
                e.printStackTrace()
            }
        }

        backgrond.start()


    }
}
