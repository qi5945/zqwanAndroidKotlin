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
import com.blankj.utilcode.util.ToastUtils
import com.zq.uilibrary.BaseFragment
import com.zq.uilibrary.R

abstract class BaseMvpViewFragment : BaseFragment(){

    /**
     * 显示Toast信息
     *
     * @param msg 消息信息
     */

    fun showToast(msg: String?, isLong: Boolean = false) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        ToastUtils.setBgColor(ContextCompat.getColor(mContext, android.R.color.black))
        ToastUtils.setMsgColor(ContextCompat.getColor(mContext, android.R.color.white))
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        if (isLong) {
            ToastUtils.showLong(msg)
        } else {
            ToastUtils.showShort(msg)
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
     * 结束Activity
     */
    fun finishActivity() {
        activity?.finish()
    }

    /**
     * 启动一个Activity然后结束自己
     *
     * @param clz Activity类
     */
    fun startActivityThenFinishSelf(clz: Class<out Activity>) {
        startActivity(Intent(mContext, clz))
        activity!!.finish()
    }


    private var alertDialog: AlertDialog? = null

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
        activity?.runOnUiThread {
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
            activity!!.runOnUiThread {
                alertDialog!!.dismiss()
            }
        }
    }
}