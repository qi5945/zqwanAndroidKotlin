package com.zq.zqwanandroidkotlin.bean



import com.zq.zqwanandroidkotlin.utils.Constant
import com.zq.zqwanandroidkotlin.utils.CustomUtils
import com.zq.zqwanandroidkotlin.utils.Preference
import java.io.Serializable


/**
 * desc：
 * author：Created by xusong on 2019/3/11 16:35.
 */

//记录当前账号
class AccountBean private constructor(var id: Int, var username: String,var icon: String,
                                      var type: Int, var collectIds: List<Int>?, var isLogin: Boolean) : Serializable {

    companion object {
        val instance: AccountBean by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            if (CustomUtils.restoreObject(Constant.accountInfo) != null) {
                var account :AccountBean = CustomUtils.restoreObject(Constant.accountInfo) as AccountBean
                AccountBean(account.id, account.username, account.icon,
                        account.type, account.collectIds, account.isLogin)
            } else {
                AccountBean(0, "", "", 0, null, false)
            }
        }
    }


    fun clear() {
        Preference.clear()
        id = 0
        username = ""
        icon = ""
        type = 0
        collectIds = null
        isLogin = false
        CustomUtils.writeToCache(Constant.accountInfo,AccountBean.instance)
    }

    override fun toString(): String {
        return "AccountBean(id=$id, username='$username', icon='$icon', type=$type, collectIds=$collectIds, isLogin=$isLogin)"
    }
}