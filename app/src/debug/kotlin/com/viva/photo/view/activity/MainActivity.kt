package com.viva.photo.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.viva.photo.R
import com.viva.photo.view.ysfragment.MainFragment

class MainActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_activity_main)

        var ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content_frame, MainFragment(), "main")
        ft.commitAllowingStateLoss()

    }

}
