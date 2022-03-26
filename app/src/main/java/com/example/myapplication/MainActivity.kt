package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.lazylibrary.util.DensityUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.test).setOnClickListener {
            // source
            Person1().name
            Person2().run {
                hello()
                hi()
            }
            // aar
            DensityUtil.dip2px(this, 2F)
        }
    }
}