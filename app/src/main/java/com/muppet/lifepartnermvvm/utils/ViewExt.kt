package com.muppet.lifepartnermvvm.utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 * des：View的扩展方法
 *
 * @author: Muppet
 * @date:   2021/2/23
 */

/**
 * viewPager2适配fragment
 */
fun ViewPager2.initFragment(
    fragment:Fragment,
    fragments:MutableList<Fragment>
) : ViewPager2{
    adapter = object : FragmentStateAdapter(fragment){
        override fun getItemCount() = fragments.size
        override fun createFragment(position: Int)= fragments[position]
    }
    return this
}