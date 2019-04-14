package com.zq.zqwanandroidkotlin.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zq.zqwanandroidkotlin.R
import com.zq.zqwanandroidkotlin.bean.HomeListBean
import java.text.SimpleDateFormat

class HomeAdapter : BaseQuickAdapter<HomeListBean.Datas,BaseViewHolder>{
    override fun convert(helper: BaseViewHolder?, item: HomeListBean.Datas?) {
        val format = SimpleDateFormat("yyyy-MM-dd")
        helper!!.setText(R.id.home_item_author, item!!.author)
            .setText(R.id.home_item_time, format.format(item.publishTime))
            .setText(R.id.home_item_title, item.title)
            .setText(R.id.home_item_type, item.chapterName)
            .setImageResource(
                R.id.home_item_collect,
                if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like)
            .addOnClickListener(R.id.home_item_collect)
            .addOnClickListener(R.id.home_item_type)
    }

    constructor(context: Context,datas:List<HomeListBean.Data>?) :super(R.layout.item_rv_home)


}
