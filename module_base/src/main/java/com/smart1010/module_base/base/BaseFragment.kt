package com.smart1010.module_base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 *  author : Snail
 *  date : 2022/11/13 18:04
 *  description :
 */
abstract class BaseFragment<T : ViewDataBinding, F : BaseViewModel> : Fragment() {

    protected lateinit var mViewModel: F
    protected lateinit var mBinding: T

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, initLayoutView(), container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(requireActivity()).get(initModelClass())

        initView()
    }

    abstract fun initLayoutView(): Int

    abstract fun initModelClass(): Class<F>

    abstract fun initView()
}