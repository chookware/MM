package com.viva.photo

import android.app.Application
import com.avos.avoscloud.*
import com.bumptech.glide.Glide
import com.chenenyu.router.Router
import com.viva.photo.view.activity.PushCallBackActivity

class MyLeanCloudApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, BuildConfig.LEAN_CLOUD_APPID, BuildConfig.LEAN_CLOUD_APPKEY)
        // 放在 SDK 初始化语句 AVOSCloud.initialize() 后面，只需要调用一次即可
        AVOSCloud.setDebugLogEnabled(true)

        AVInstallation.getCurrentInstallation().saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {

            }

        })
        PushService.setDefaultPushCallback(this, PushCallBackActivity::class.java)

        // 初始化
        Router.initialize(this)
        // 开启log
    }

}