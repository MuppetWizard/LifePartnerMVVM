package com.muppet.lifepartnermvvm.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 * des：基础 mmvvm activity
 *
 * @author: Muppet
 * @date:   2021/2/22
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mActivityProvider: ViewModelProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutBinding()?.let { setContentView(it.root) }
        setStatusBar()
        initViewModel()
        init(savedInstanceState)
    }

    /**
     *  getActivityViewModel() 方法的简便调用方式
     * 例如：getActivityViewModel<HomeVm>()
     */
    protected inline fun <reified T: ViewModel?> getActivityViewModel(): T = getActivityViewModel(T::class.java)
    /**
     *通过activity获取viewModel，目的让其跟随activity生命周期
     */
    protected fun <T: ViewModel?> getActivityViewModel(vmClazz:Class<T>) : T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider!!.get(vmClazz)
    }

    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 使用viewBinding绑定layout布局
     */
    abstract fun getLayoutBinding(): ViewBinding?

    /**
     * 设置状态栏
     */
    open fun setStatusBar() {}

    /**
     * 初始化ViewModel
     * 未设计为抽象方法，是由于部分简单activity可能不需要viewmodel
     */
    open fun initViewModel() {  }

}