package com.zq.zqwanandroidkotlin

import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.zq.uilibrary.BaseActivity
import com.zq.uilibrary.utils.ToastUtils
import com.zq.zqwanandroidkotlin.ui.fragment.HomeFragemnt
import com.zq.zqwanandroidkotlin.ui.fragment.HotFragment
import com.zq.zqwanandroidkotlin.ui.fragment.KnowLedgeFrgment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val INDEX_HOME = "HOME"
    val INDEX_KNOWLEDGE = "KNOWLEDGE"
    val INDEX_HOT = "HOT"

    private lateinit var mNavIv: ImageView
    private lateinit var mNavTv: TextView
    private lateinit var mTvLoginout: TextView


    private val fragments = HashMap<String, Fragment>()


    override fun getActivityLayoutId(): Int = R.layout.activity_main

    override fun initData() {
        toolbar.run {
            setSupportActionBar(toolbar)
        }

        drawer.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.my_like,
                R.string.my_about
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        navigationview.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
        }

        mNavIv = navigationview.getHeaderView(0).findViewById(R.id.header_iv)
        mNavTv = navigationview.getHeaderView(0).findViewById(R.id.header_tv)
        mTvLoginout = navigationview.getHeaderView(0).findViewById(R.id.tv_login_out)


        bottom_navigation.run {
            setOnNavigationItemSelectedListener(bottomNavigationSelectedListener)
        }
        showFragment(INDEX_HOME)


    }

    private val bottomNavigationSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    LogUtils.d("home click")
                    showFragment(INDEX_HOME)
                }

                R.id.navigation_type -> {
                    LogUtils.d("know click")
                    showFragment(INDEX_KNOWLEDGE)
                }
                R.id.mine -> {
                    showFragment(INDEX_HOT)
                    LogUtils.d("mine click")

                }

            }
            true
        }

    fun showFragment(tag: String) {
        LogUtils.d("showFragment $tag")

        val beginTransaction = supportFragmentManager.beginTransaction()
        var fragment = fragments[tag]
        if (fragment == null) {
            when (tag) {
                INDEX_HOME -> fragment = HomeFragemnt()
                INDEX_KNOWLEDGE -> fragment = KnowLedgeFrgment()
                INDEX_HOT -> fragment = HotFragment()
            }
            fragments[tag] = fragment!!
        }
        if (!supportFragmentManager.fragments.contains(fragment)) {
            try {
                beginTransaction.add(R.id.fl_container, fragment, tag)
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
        if (supportFragmentManager.fragments.size != 0) {
            for (i in 0 until supportFragmentManager.fragments.size)
                beginTransaction.hide(supportFragmentManager.fragments[i])
        }
        beginTransaction.show(fragment).commitAllowingStateLoss()
    }




    private val onDrawerNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_like -> {
//                    if (AccountBean.instance.isLogin) {
//                        startActivity(Intent(this, CollectActivity::class.java))

//                    } else {
                    ToastUtils.showLong("请先登录")
//                        startActivity(Intent(this, AccountActivity::class.java))
//                    }
                }

                R.id.nav_about -> {
                    ToastUtils.showLong("about")
                }
            }
            true
        }

    override fun initEvent() {

    }


}
