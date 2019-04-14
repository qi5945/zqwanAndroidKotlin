package com.zq.wanandroid.base.mvp.presenter

import com.zq.uilibrary.mvp.BaseMvpView
import org.jetbrains.annotations.NotNull
import java.lang.ref.WeakReference

abstract class BaseMvpPresenter<M,V: BaseMvpView>{
    private var vWeakReference:WeakReference<V> ? = null
    private var model:M? = null

    var isLoading : Boolean ? = false


    fun attachView(view : V){
        vWeakReference = WeakReference(view)
    }

    open fun detachView(){
        vWeakReference?.clear()
        vWeakReference = null
    }

    protected fun getView() : V?{
        return vWeakReference?.get()
    }

    protected fun getModel():M{
        return  model ?: createModel().apply { model = this }
    }

    @NotNull
    protected abstract fun createModel(): M


}