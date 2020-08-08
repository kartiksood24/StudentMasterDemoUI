package com.kartik.test.schooluidemoproject.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


// To show the toast
fun Context.showToast(
        pMessage: String?
) {
    Toast.makeText(this, pMessage, Toast.LENGTH_SHORT).show()
}

private inline fun FragmentManager.doTransaction(
        func: FragmentTransaction.() ->
        FragmentTransaction
) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(
        fragment: Fragment,
        frameId: Int,
        addToStack: Boolean = false
) {
    supportFragmentManager.doTransaction {
        if (addToStack) add(frameId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
        else add(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragment(
        fragment: Fragment,
        frameId: Int,
        addToStack: Boolean = false,
        clearBackStack: Boolean = false
) {
    supportFragmentManager.doTransaction {

        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(
                    first.id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.doTransaction { remove(fragment) }
}

fun AppCompatActivity.getCurrentFragment(): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String? = ""

    if (fragmentManager.backStackEntryCount > 0)
        fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name

    return fragmentManager.findFragmentByTag(fragmentTag)
}

fun AppCompatActivity.getPreviousFragment(): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String? = ""

    if (fragmentManager.backStackEntryCount > 0)
        fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 2).name

    return fragmentManager.findFragmentByTag(fragmentTag)
}

fun AppCompatActivity.popBackStack() {
    hideKeyboard(this.currentFocus)
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.popBackStackInclusive() {
    hideKeyboard(this.currentFocus)
    if (supportFragmentManager.backStackEntryCount > 0)
        supportFragmentManager.popBackStack(
                supportFragmentManager.getBackStackEntryAt(0).id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
}

fun Context.hideKeyboard(view: View?) {
    val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    if (inputMethodManager != null && view != null) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
