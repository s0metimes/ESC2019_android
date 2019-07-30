package com.example.esc2019

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, ArticleListActivity::class.java)
            startActivity(intent)
            // finish 안해주면 activity 종료 안되면서 태스크에 쌓여있습니다.
            finish()
        }, 2000)
    }
}
