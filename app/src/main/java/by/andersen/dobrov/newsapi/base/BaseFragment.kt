package by.andersen.dobrov.newsapi.base

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import by.andersen.dobrov.newsapi.util.fragment.setupActionBar

abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int,
    @IdRes private val toolbarRes: Int? = null,
    @MenuRes private val menuRes: Int? = null,
) : Fragment(contentLayoutId) {

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val isIntercepted = this@BaseFragment.handleOnBackPressed()
            if (isIntercepted) return

            isEnabled = false
            activity?.onBackPressed()
        }
    }

    protected lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes != null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menuRes?.let { inflater.inflate(it, menu) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
        registerOnBackPressedCallback()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        childFragmentManager.fragments.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

    open fun handleOnBackPressed(): Boolean = false

    @Suppress("MemberVisibilityCanBePrivate")
    fun getBaseActivity(): BaseActivity? = activity as? BaseActivity

    private fun initToolBar() {
        toolbarRes ?: return
        toolbar = setupActionBar(toolbarRes)!!
    }

    private fun registerOnBackPressedCallback() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }
}