package com.viva.photo.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.viva.photo.R
import com.viva.photo.control.LoadHtml

class TestedActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var loadHtml = LoadHtml()
        loadHtml.load()
    }

}