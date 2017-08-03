package com.viva.photo.control

import com.avos.avoscloud.AVCloud
import com.avos.avoscloud.AVException
import com.avos.avoscloud.FunctionCallback

object UserUils {

    private val COLUMN_USER = "user"
    private val COLUMN_PASSWORD = "password"

    fun register(user: String, password: String) {
        AVCloud.callFunctionInBackground("register",
                mapOf(COLUMN_USER to user, password to COLUMN_PASSWORD),
                object : FunctionCallback<Map<String, String>>() {
                    public override fun done(result: Map<String, String>?, e: AVException?) {
                        if (e != null) {

                        } else {
                        }
                    }
                })
    }

    fun login(user: String, password: String) {
        AVCloud.callFunctionInBackground("login",
                mapOf(COLUMN_USER to user, password to COLUMN_PASSWORD),
                object : FunctionCallback<Map<String, String>>() {
                    public override fun done(result: Map<String, String>?, e: AVException?) {
                        if (e != null) {
                            System.out.println("response " + e)
                        } else {
                            System.out.println("response " + result)
                        }
                    }
                })
    }

}
