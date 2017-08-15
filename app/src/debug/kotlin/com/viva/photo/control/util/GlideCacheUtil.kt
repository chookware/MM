package com.viva.photo.control.util

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;

import java.io.File;
import java.math.BigDecimal;

class GlideCacheUtil{
    companion object {
        var inst: GlideCacheUtil? = null
        fun getInstance(): GlideCacheUtil? {
            if (inst == null) {
                inst = GlideCacheUtil()
            }
            return inst
        }
    }


}
