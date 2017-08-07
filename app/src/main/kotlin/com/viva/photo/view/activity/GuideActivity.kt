package com.viva.photo.view.activity

import com.viva.photo.R
import kotlinx.android.synthetic.main.layout_activity_guide.*

class GuideActivity : android.app.Activity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_guide)

        banner_guide_content.setData(R.drawable.test_1, R.drawable.test_2, R.drawable.test_3)
    }

}