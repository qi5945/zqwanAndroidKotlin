package com.zq.zqwanandroidkotlin.ui.mvp.presenter

import android.support.v7.view.menu.MenuView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.zq.wanandroid.base.mvp.presenter.BaseMvpPresenter
import com.zq.zqwanandroidkotlin.bean.BannerBean
import com.zq.zqwanandroidkotlin.bean.HomeListBean
import com.zq.zqwanandroidkotlin.ui.mvp.contract.HomeContract
import com.zq.zqwanandroidkotlin.ui.mvp.model.HomeModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class HomePresenter : BaseMvpPresenter<HomeContract.Model,HomeContract.View>(),HomeContract.Presenter{
    override fun getBanner() {
        getModel().getBanner().subscribe(object : Observer<BannerBean> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(value: BannerBean?) {
                getView()!!.loadBannerSucc(value!!)
            }

            override fun onError(e: Throwable?) {
                ToastUtils.showLong("error$e")
            }

        })
    }

    override fun createModel(): HomeContract.Model  = HomeModel()

    override fun getHomeList(curpage: Int) {
        getView()!!.startLoading()
        getModel().getHomeList(curpage).subscribe(object : Observer<HomeListBean> {
            override fun onComplete() {
                LogUtils.d("onComplete")
                getView()!!.loadComplete()
            }

            override fun onSubscribe(d: Disposable?) {

            }

            override fun onNext(value: HomeListBean?) {
                LogUtils.d(value.toString())
                getView()!!.loadHomeListSuccess(value!!)
            }

            override fun onError(e: Throwable?) {
                ToastUtils.showLong("error$e")

                getView()!!.loadError(curpage)
            }
        })
    }

}