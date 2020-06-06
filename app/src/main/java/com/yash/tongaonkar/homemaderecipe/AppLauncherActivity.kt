package com.yash.tongaonkar.homemaderecipe

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppLauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_launcher)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
