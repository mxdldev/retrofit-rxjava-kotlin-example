package com.mxdl.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mxdl.retrofit.java.CallActivity
import com.mxdl.retrofit.java.RxActivity
import com.mxdl.retrofit.kotlin.MainKotlinActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_call.setOnClickListener {
            startActivity(Intent(this, CallActivity::class.java))
        }
        btn_rx.setOnClickListener {
            startActivity(Intent(this, RxActivity::class.java))
        }
        btn_kotlin.setOnClickListener {
            startActivity(Intent(this, MainKotlinActivity::class.java))
        }
    }

}
