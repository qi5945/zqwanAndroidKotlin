package com.zq.zqwanandroidkotlin.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.zq.uilibrary.mvp.BaseMvpFragment
import com.zq.zqwanandroidkotlin.R
import com.zq.zqwanandroidkotlin.adapter.HomeAdapter
import com.zq.zqwanandroidkotlin.bean.BannerBean
import com.zq.zqwanandroidkotlin.bean.HomeListBean
import com.zq.zqwanandroidkotlin.ui.mvp.contract.HomeContract
import com.zq.zqwanandroidkotlin.ui.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragemnt:BaseMvpFragment<HomeContract.Model,HomeContract.View,HomePresenter>(),HomeContract.View {
    override fun loadBannerSucc(bannerBean: BannerBean) {
    }

    override fun createPresenter(): HomePresenter  = HomePresenter()

    override fun loadHomeListSuccess(homeListBean: HomeListBean) {
        if(curpage == 0){
            homeAdapter.setNewData(homeListBean.data.datas)
        }else{
            homeAdapter.addData(homeListBean.data.datas!!)
            if (homeListBean.data.over){
                homeAdapter.loadMoreEnd()
            }else{
                homeAdapter.loadMoreComplete()
            }
        }
    }

    override fun startLoading() {
        home_swip.isRefreshing = true
    }

    override fun loadComplete() {
        home_swip.isRefreshing = false
    }

    override fun loadError(curpage: Int) {
        home_swip.isRefreshing = false
    }

    override fun getFragmentLayoutId(): Int = R.layout.fragment_home


    private val mvpPresenter = getPresenter()

    override fun initEvent(view: View?) {
    }

    override fun initData() {
        LogUtils.d("initdata")
        mvpPresenter.getHomeList(curpage)
    }

    private var curpage : Int = 0

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(activity!!, null)
    }

    override fun initView(view: View?) {
        home_rv.layoutManager = LinearLayoutManager(activity)
        home_rv.adapter = homeAdapter
        home_swip.setColorSchemeColors(activity!!.resources.getColor(R.color.colorPrimary))

        home_swip.run {
            setOnRefreshListener {
                refresh()
            }
        }
        homeAdapter.run {
            setOnLoadMoreListener({
                curpage++
                mvpPresenter.getHomeList(curpage)
            },home_rv)
        }
    }

    fun refresh() {
        curpage = 0
        mvpPresenter.getHomeList(curpage)
    }
}