package com.zq.uilibrary.mvp

import com.zq.wanandroid.base.mvp.presenter.BaseMvpPresenter

abstract class BaseMvpActivity<M,V:BaseMvpView,P: BaseMvpPresenter<M,V>> : BaseMvpViewActivity(){
    private var presenter : P ?= null



    protected abstract fun createPresenter() : P


    /**
     * 获取P层实例
     *
     * @return P层实例
     */
    protected fun getPresenter(): P {
        return presenter ?: createPresenter().apply {
            presenter = this
            presenter!!.attachView(this@BaseMvpActivity as V)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView()
    }



}