package com.muppet.lifepartnermvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.muppet.lifepartnermvvm.common.BaseActivity
import com.muppet.lifepartnermvvm.databinding.ActivityMainBinding
import com.muppet.lifepartnermvvm.ui.MainFragment

/**
 * 主界面，主要作用是用于承载Fragment
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

   /* override fun init(savedInstanceState: Bundle?) {

    }

    *//**
     * 设置状态栏沉浸模式
     *//*
    override fun setStatusBar() {

    }

    override fun initViewModel() {

    }

    override fun getLayoutBinding()= binding*/

    override fun onBackPressed() {
        //获取hostFragment
        val mMainFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.host_fragment)
        //获取当前所在fragment
        val fragment =
            mMainFragment?.childFragmentManager?.primaryNavigationFragment
        //如果当前处于根fragment
        if (fragment is MainFragment) {
            //Activity退出，但不销毁
            moveTaskToBack(false)
        }else{
            super.onBackPressed()
        }
    }
}