package com.muppet.lifepartnermvvm.ui.main.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.muppet.lifepartnermvvm.R
import com.muppet.lifepartnermvvm.common.LazyFragment
import com.muppet.lifepartnermvvm.databinding.FragmentWeatherBinding

/**
 * 天气界面
 */
class WeatherFragment : LazyFragment() {

    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun lazyInit() {

    }

    override fun getLayoutBinding() = binding

}