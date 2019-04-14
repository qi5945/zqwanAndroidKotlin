package com.zq.uilibrary.mvp

import com.zq.wanandroid.base.mvp.presenter.BaseMvpPresenter
import org.jetbrains.annotations.NotNull

abstract class BaseMvpFragment<M,V : BaseMvpView , P : BaseMvpPresenter<M, V>> : BaseMvpViewFragment(){

    private var presenter : P ?= null

    /**
     * 创建P层
     *
     * @return P层对象
     */
    @NotNull
    protected abstract fun createPresenter(): P


    /**
     * 获取P层实例
     *
     * @return P层实例
     */
    protected fun getPresenter(): P {
        return presenter ?: createPresenter().apply {
            presenter = this
            presenter!!.attachView(this@BaseMvpFragment as V)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().detachView()
    }


}