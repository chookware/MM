package com.viva.photo.control

import com.avos.avoscloud.AVCloud
import com.avos.avoscloud.AVException
import com.avos.avoscloud.FunctionCallback

object UserUils {

    private val COLUMN_USER = "user"
    private val COLUMN_PASSWORD = "password"
    private val COLUMN_VERSION = "version"

    fun register(user: String, password: String, version: Int, listener: OnUserSignListener?) {
        AVCloud.callFunctionInBackground("register",
                mapOf(COLUMN_USER to user, COLUMN_PASSWORD to password, COLUMN_VERSION to version),
                object : FunctionCallback<Map<String, Any>>() {
                    override fun done(result: Map<String, Any>?, e: AVException?) {
                        if (e != null) {
                            listener?.onSignUp(-1)
                        } else {
                            if (result != null) {
                                listener?.onSignUp(result["code"] as Int)
                            } else {
                                listener?.onSignUp(-1)
                            }
                        }
                    }
                })
    }

    fun login(user: String, password: String, version: Int, listener: OnUserSignListener?) {
        AVCloud.callFunctionInBackground("login",
                mapOf(COLUMN_USER to user, COLUMN_PASSWORD to password, COLUMN_VERSION to version),
                object : FunctionCallback<Map<String, Any>>() {
                    override fun done(result: Map<String, Any>?, e: AVException?) {
                        if (e != null) {
                            listener?.onSignIn(-1, null)
                        } else {
                            if (result != null) {
                                listener?.onSignIn(result["code"] as Int, result["data"])
                            } else {
                                listener?.onSignIn(-1, null)
                            }
                        }
                    }
                })
    }

}
