package com.zq.uilibrary

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        beforeSetContentView()
        setContentView(getActivityLayoutId())
        afterSetContentView()

        changeStatusBar()

    }


    /**
     * 改变状态栏的颜色 需要时复写
     */
    fun changeStatusBar() {
    }

    /**
     * @return 布局id
     */
    abstract fun getActivityLayoutId() : Int

    /**
     *  设置contentView之前的执行的操作 需要时重写
     */
    fun beforeSetContentView(){}

    /**
     * 在设置ContenView之后执行的操作
     * 需要时复写
     */
    open fun afterSetContentView() {
        initView()
        initData()
        initEvent()
    }

    /**
     * 初始化控件及设置
     */
    open fun initView() {}

    /**
     * 初始化数据状态
     */
    protected abstract fun initData()


    /**
     * 初始化事件
     */
    protected abstract fun initEvent()





}