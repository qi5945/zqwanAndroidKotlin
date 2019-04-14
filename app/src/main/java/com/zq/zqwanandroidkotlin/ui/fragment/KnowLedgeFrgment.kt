package com.zq.zqwanandroidkotlin.ui.fragment

import android.view.View
import com.zq.uilibrary.BaseFragment
import com.zq.zqwanandroidkotlin.R
import kotlinx.android.synthetic.main.fragment_common.*

class KnowLedgeFrgment:BaseFragment() {
    override fun getFragmentLayoutId(): Int = R.layout.fragment_common

    override fun initView(view: View?) {
    }

    override fun initEvent(view: View?) {
    }

    override fun initData() {
        tv_content.text = "KnowLedgeFrgment"

    }
}