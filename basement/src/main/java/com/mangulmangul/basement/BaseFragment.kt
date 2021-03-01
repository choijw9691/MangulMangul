package com.mangulmangul.basement

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    protected var canAccessUi: Boolean = true
        private set

    protected lateinit var rootView: View

    protected abstract fun createRootView(inflater: LayoutInflater, container: ViewGroup?): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        canAccessUi = true
        rootView = createRootView(inflater, container)
        return rootView
    }

    override fun onDestroyView() {
        canAccessUi = false
        super.onDestroyView()
    }

    protected fun baseActivity(): BaseActivity? {
        if (activity is BaseActivity) {
            return activity as BaseActivity
        }

        return null
    }

    protected val statusBarHeight: Int?
        get() {
            return baseActivity()?.getStatusBarHeight()
        }

    protected val navigationBarHeight: Int?
        get() {
            return baseActivity()?.getNavigationBarHeight()
        }

    protected fun hideKeyboard() {
        baseActivity()?.hideKeyboard()
    }

    protected fun showKeyboard(view: View) {
        baseActivity()?.showKeyboard(view)
    }
}