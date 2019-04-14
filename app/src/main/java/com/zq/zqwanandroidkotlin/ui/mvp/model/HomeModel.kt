package com.zq.zqwanandroidkotlin.ui.mvp.model

import com.zq.wanandroid.net.ApiService
import com.zq.wanandroid.net.RetrofitHelper
import com.zq.zqwanandroidkotlin.bean.BannerBean
import com.zq.zqwanandroidkotlin.bean.HomeListBean
import com.zq.zqwanandroidkotlin.ui.mvp.contract.HomeContract
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel :HomeContract.Model {
    override fun getBanner(): Observable<BannerBean> {
        return RetrofitHelper.instance.create(ApiService::class.java).getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getHomeList(curpage: Int): Observable<HomeListBean> {
        return RetrofitHelper.instance.create(ApiService::class.java).getHomeList(curpage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}
