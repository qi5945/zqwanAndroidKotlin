package com.zq.uilibrary.mvp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.zq.uilibrary.BaseActivity
import com.zq.uilibrary.R
import com.zq.uilibrary.utils.ToastUtils

abstract class BaseMvpViewActivity : BaseActivity(){
    fun showToast(msg : String? ,isLong:Boolean = false){
        ToastUtils.setBgColor(ContextCompat.getColor(mContext,android.R.color.background_dark))
        ToastUtils.setMsgColor(ContextCompat.getColor(mContext, android.R.color.white))
        ToastUtils.setGravity(Gravity.CENTER,0,0)
        if (isLong){
            ToastUtils.showLong(msg)
        }else{
            ToastUtils.showShort(msg)
        }
    }

    private var alertDialog: AlertDialog?= null

    private lateinit var dialogMsgTextView: TextView


    /**
     * 显示加载进度框
     */
    fun showLoadingDialog(msg: String? = null) {
        if (alertDialog == null) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null)
            dialogMsgTextView = view.findViewById(R.id.tv_msg) as TextView
            alertDialog = AlertDialog
                .Builder(mContext)
                .setView(view)
                .create()
            alertDialog!!.setCanceledOnTouchOutside(false)
            if (alertDialog!!.window != null) {
                alertDialog!!.window!!.setBackgroundDrawable(BitmapDrawable())
            }
        }

        runOnUiThread {
            if (!TextUtils.isEmpty(msg)) {
                dialogMsgTextView.text = msg
            }
            alertDialog?.show()
        }
    }

    /**
     * 隐藏加载进度框
     */
    fun dismissLoadingDialog() {
        if (alertDialog != null) {
            runOnUiThread {
                alertDialog!!.dismiss()
            }
        }
    }

    /**
     * 启动一个Activity
     *
     * @param clz Activity类
     */
    fun startActivity(clz: Class<out Activity>) {
        startActivity(Intent(mContext, clz))
    }

    /**
     * 结束本Activity
     */
    fun finishActivity() {
        finish()
    }

    /**
     * 启动一个Activity然后结束自己
     *
     * @param clz Activity类
     */
    fun startActivityThenFinishSelf(clz: Class<out Activity>) {
        startActivity(Intent(mContext, clz))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDialog()
    }

}