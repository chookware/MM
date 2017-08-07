package com.viva.photo.view.activity

import android.app.Activity
import android.os.Bundle
import com.viva.photo.control.UserUils

class LoginActivity : android.app.Activity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        UserUils.login("125", "12", 0, null)
    }

}