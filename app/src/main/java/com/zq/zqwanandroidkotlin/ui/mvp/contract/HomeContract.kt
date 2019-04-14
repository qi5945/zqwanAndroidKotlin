package com.zq.zqwanandroidkotlin.ui.mvp.contract

import com.zq.uilibrary.BaseLecMvpView
import com.zq.uilibrary.mvp.BaseMvpView
import com.zq.zqwanandroidkotlin.bean.BannerBean
import com.zq.zqwanandroidkotlin.bean.HomeListBean
import io.reactivex.Observable

class HomeContract{
    interface View : BaseLecMvpView{
        fun loadHomeListSuccess(homeListBean: HomeListBean)

        fun loadBannerSucc(bannerBean: BannerBean)
    }

    interface Presenter{
        fun getHomeList(curpage:Int)

        fun getBanner()

    }

    interface Model{
        fun getHomeList(curpage: Int) : Observable<HomeListBean>

        fun getBanner() : Observable<BannerBean>

    }
}