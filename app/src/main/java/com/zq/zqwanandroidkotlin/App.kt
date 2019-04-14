package com.zq.zqwanandroidkotlin

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.zq.zqwanandroidkotlin.utils.Preference


/**
 * desc：
 * author：Created by xusong on 2019/3/11 18:56.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        // 初始化 SharedPreference
        Preference.setContext(this)
        //初始化第三方工具类
        Utils.init(this)
    }

    companion object {
        var context:Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }
}