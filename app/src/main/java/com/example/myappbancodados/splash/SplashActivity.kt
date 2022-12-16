package com.example.myappbancodados.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myappbancodados.R
import com.example.myappbancodados.login.ui.initial.InitialLoginActivity
import java.util.*

private val timer = Timer()

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer.schedule(object : TimerTask() {
            override fun run() {
                jump()
            }
        }, 3000)
    }

    private fun jump() {
        timer.cancel()
        startActivity(Intent(this, InitialLoginActivity::class.java))
        this.finish()
    }
}