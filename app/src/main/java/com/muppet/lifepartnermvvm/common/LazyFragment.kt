package com.muppet.lifepartnermvvm.common

import android.os.Bundle

/**
 * des：
 * 实现fragment 懒加载
 * @author: Muppet
 * @date:   2021/3/2
 */
abstract class LazyFragment : BaseFragment() {
    private var isLoaded = false
    override fun onResume() {
        super.onResume()
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        isLoaded = false
    }

    abstract fun lazyInit()
}