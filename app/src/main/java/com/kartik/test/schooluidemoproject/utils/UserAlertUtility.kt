package com.kartik.test.schooluidemoproject.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.constant.Constants

class UserAlertUtility {
    companion object {
        private var mAlertDialog: AlertDialog? = null
        private var mCustomDialog: AppCompatDialog? = null

        // Show a dialog alert for some actions
        fun showAlertDialog(
            pTitle: String?,
            pMessage: String?,
            pContext: Activity?,
            pOkListener: DialogInterface.OnClickListener? = null,
            pCancelListener: DialogInterface.OnClickListener? = null,
            pPositiveButton: String? = Constants.sEmptyString,
            pNegativeButton: String? = Constants.sEmptyString
        ) {
            if (pContext != null) {
                if (pContext.isFinishing) {
                    return
                }
                /**
                 * If the alert dialog is already showing neednot create a new one
                 */
                if (mAlertDialog == null || !mAlertDialog!!.isShowing) {
                    mAlertDialog =
                        AlertDialog.Builder(ContextThemeWrapper(pContext, R.style.AlertDialogCustom))
                            .create()
                    if (pTitle == null) {
                        with(mAlertDialog) { this?.setTitle(pContext.getString(R.string.app_name)) }
                    } else {
                        with(mAlertDialog) { this?.setTitle(pTitle) }
                    }
                    mAlertDialog!!.setMessage(pMessage)
                    mAlertDialog!!.setCancelable(false)
                    var positiveText: String? = pContext.getString(R.string.ok_message)
                    mAlertDialog.run {
                        if (pPositiveButton != null && pPositiveButton.isNotEmpty()) {
                            positiveText = pPositiveButton
                        }
                        this!!.setButton(DialogInterface.BUTTON_POSITIVE, positiveText, pOkListener)
                        if (pNegativeButton != null && pNegativeButton.isNotEmpty()) {
                            this.setButton(
                                DialogInterface.BUTTON_NEGATIVE,
                                pNegativeButton,
                                pCancelListener
                            )
                        }
                        show()
                    }
                }
            }
        }

        // hide progress
        private fun hideAlertDialog() {
            if (mAlertDialog != null && mAlertDialog!!.isShowing) {
                mAlertDialog!!.dismiss()
                mAlertDialog = null
            }
        }

        fun initCustomDialog(
            pContext: Context?,
            pLayout: Int
        ): ViewDataBinding? {
            var binding: ViewDataBinding? = null
            if (mCustomDialog == null || !mCustomDialog?.isShowing!!) {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(pContext),
                    pLayout,
                    null,
                    false
                )
                mCustomDialog = AppCompatDialog(pContext)
                mCustomDialog?.create()
                mCustomDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mCustomDialog?.setContentView(binding.root)
                if (mCustomDialog?.window != null) {
                    mCustomDialog?.window?.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.MATCH_PARENT
                    )
                    mCustomDialog?.window?.setBackgroundDrawable(
                        ColorDrawable(
                            ContextCompat.getColor(pContext!!, R.color.colorTransparentDark)
                        )
                    )
                }
                mCustomDialog?.setCancelable(false)
            }
            return binding
        }

        fun showCustomDialog() {
            if (mCustomDialog != null && !mCustomDialog!!.isShowing) {
                mCustomDialog!!.show()
            }
        }

        fun hideCustomDialog() {
            if (mCustomDialog != null && mCustomDialog!!.isShowing) {
                mCustomDialog!!.dismiss()
                mCustomDialog = null
            }
        }
    }
}