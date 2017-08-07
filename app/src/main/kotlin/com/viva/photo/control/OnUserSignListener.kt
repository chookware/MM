package com.viva.photo.control

interface OnUserSignListener {
    fun onSignIn(code: Int, data: Any?)
    fun onSignUp(code: Int)
}
