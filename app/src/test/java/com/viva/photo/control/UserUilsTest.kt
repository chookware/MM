package com.viva.photo.control

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Black_Horse on 2017/8/3.
 */
class UserUilsTest {
    @Before
    fun setUp() {

    }

    @Test
    fun login() {
        UserUils.login("123", "12")
    }

}