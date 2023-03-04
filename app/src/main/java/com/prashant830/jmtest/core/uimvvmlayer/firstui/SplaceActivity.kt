package com.prashant830.jmtest.core.uimvvmlayer.firstui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prashant830.jmtest.R
import com.prashant830.jmtest.core.uimvvmlayer.secondui.CategoryIcoActivity
import com.prashant830.jmtest.core.uimvvmlayer.thirdui.SearchActivity


class SplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace)
        supportActionBar!!.hide()

        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep((5 * 1000).toLong())
                    val i = Intent(baseContext, CategoryIcoActivity::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {
                }
            }
        }
        background.start()
    }
}