package com.muppet.lifepartnermvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.muppet.lifepartnermvvm.R
import com.muppet.lifepartnermvvm.common.BaseFragment
import com.muppet.lifepartnermvvm.databinding.FragmentMainBinding
import com.muppet.lifepartnermvvm.ui.main.express.ExpressFragment
import com.muppet.lifepartnermvvm.ui.main.home.HomeFragment
import com.muppet.lifepartnermvvm.ui.main.mine.MineFragment
import com.muppet.lifepartnermvvm.ui.main.weather.WeatherFragment
import com.muppet.lifepartnermvvm.utils.initFragment
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * 主界面
 */
class MainFragment : BaseFragment() {

    private val fragmentList = arrayListOf<Fragment>()

    /**
     * 首页
     */
    private val homeFragment by lazy { HomeFragment() }

    /**
     * 天气
     */
    private val weatherFragment by lazy { WeatherFragment() }

    /**
     * 快递
     */
    private val expressFragment by lazy { ExpressFragment() }

    /**
     * 我的
     */
    private val mineFragment by lazy { MineFragment() }

    private lateinit var binding: FragmentMainBinding

    init {
        fragmentList.apply {
            add(homeFragment)
            add(weatherFragment)
            add(expressFragment)
            add(mineFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayoutBinding() = binding

    override fun init(savedInstanceState: Bundle?) {
        //初始化ViewPager2
        binding.vpHome.apply {
            initFragment(this@MainFragment,fragmentList).run {
                //缓存全部fragment，避免切换时重新加载
                offscreenPageLimit = fragmentList.size
            }
            //取消viewpager滑动
            isUserInputEnabled = false

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.btnNav.menu.getItem(position).isChecked = true
                }
            })
        }
        //初始化底部导航栏
        binding.btnNav.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> binding.vpHome.setCurrentItem(0,false)
                    R.id.menu_weather -> binding.vpHome.setCurrentItem(1,false)
                    R.id.menu_express -> binding.vpHome.setCurrentItem(2,false)
                    R.id.menu_mine -> binding.vpHome.setCurrentItem(3,false)
                }
                //这里返回true ,否则点击会失效
                true
            }
        }
    }


}