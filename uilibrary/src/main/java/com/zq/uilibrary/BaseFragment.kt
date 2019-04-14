package com.zq.uilibrary

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(){
    protected lateinit var mContext: Context


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayoutId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        initView(view)
        initData()
        initEvent(view)
    }



    /**
     * 获取Fragment的布局资源文件id
     */
    protected abstract fun getFragmentLayoutId(): Int

    /**
     * 初始化视图控件
     */
    protected abstract fun initView(view: View?)

    /**
     * 初始化事件
     */
    protected abstract fun initEvent(view: View?)

    /**
     * 初始化页面数据状态
     */
    protected abstract fun initData()
}