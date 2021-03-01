package com.muppet.lifepartnermvvm.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.muppet.lifepartnermvvm.utils.ParamUtil

/**
 * des：基础 mvvm Fragment
 *
 * @author: Muppet
 * @date:   2021/2/24
 */
abstract class BaseFragment : Fragment() {
    /**
     * 开放给外部使用
     */
    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity
    private var fragmentProvider: ViewModelProvider? = null
    private var activityProvider: ViewModelProvider? = null
    private var mBinding: ViewBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as AppCompatActivity
        // 必须要在Activity与Fragment绑定后，因为Fragment可能获取的是Activity中ViewModel
        // 必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel与DataBinding绑定
        initViewModel()
        ParamUtil.initParam(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutBinding()?.let {
            setStatusBar()
            mBinding = it
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        onClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        //由于可能fragment的生命周期超出其视图的生命周期，因此需要将其设置为空，防止内存泄漏
        mBinding = null
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
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(mActivity)
        }
        return activityProvider!!.get(vmClazz)
    }

    /**
     * 同上
     */
    protected inline fun <reified T : ViewModel?> getFragmentViewModel(): T = getFragmentViewModel(T::class.java)
    /**
     * 通过fragment获取view model，目的让其跟随fragment生命周期
     */
    protected fun <T: ViewModel?> getFragmentViewModel(vmClazz: Class<T>) : T {
        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider!!.get(vmClazz)
    }

    /**
     *初始化入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     *使用viewBinding绑定layout布局
     */
    abstract fun getLayoutBinding(): ViewBinding?

    /**
     * 初始化ViewModel
     * 未设计为抽象方法，是由于部分简单activity可能不需要viewmodel
     */
    open fun initViewModel() {  }

    /**
     * 设置状态栏
     */
    open fun setStatusBar() {  }

    /**
     * 点击事件
     */
    open fun onClick() {}

    /**
     * fragment 跳转
     */
    protected fun nav(): NavController{
        return NavHostFragment.findNavController(this)
    }
}