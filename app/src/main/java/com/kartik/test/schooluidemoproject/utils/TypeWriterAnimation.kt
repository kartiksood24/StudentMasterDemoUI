package com.kartik.test.schooluidemoproject.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView


class Typewriter : MaterialTextView {
    private var mText: CharSequence? = null
    private var mIndex = 0
    private var mDelay: Long = 500 //Default 500ms delay
    private var textDisplayListener: OnTextDisplayListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private val mHandler: Handler = Handler(Looper.getMainLooper())
    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            text = mText!!.subSequence(0, mIndex++)
            if (mIndex <= mText!!.length) {
                mHandler.postDelayed(this, mDelay)
            } else {
                mHandler.postDelayed({ textDisplayListener?.onTextComplete() }, 500)
            }
        }
    }

    fun setTextDisplayListener(textDisplayListener: OnTextDisplayListener) {
        this.textDisplayListener = textDisplayListener
    }

    fun animateText(text: CharSequence?) {
        mText = text
        mIndex = 0
        setText("")
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)
    }

    fun setCharacterDelay(millis: Long) {
        mDelay = millis
    }

    interface OnTextDisplayListener {
        fun onTextComplete()
    }
}