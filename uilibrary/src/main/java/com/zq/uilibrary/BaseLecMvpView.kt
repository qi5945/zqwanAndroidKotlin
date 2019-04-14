package com.zq.uilibrary

import com.zq.uilibrary.mvp.BaseMvpView

interface BaseLecMvpView : BaseMvpView {

    fun startLoading()

    fun loadComplete()

    fun loadError(curpage: Int)


}