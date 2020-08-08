package com.kartik.test.schooluidemoproject.modules.launcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.ActivityLauncherBinding
import com.kartik.test.schooluidemoproject.modules.profile.StudentProfileActivity
import com.kartik.test.schooluidemoproject.utils.Typewriter


class LauncherActivity : AppCompatActivity(), Typewriter.OnTextDisplayListener {
    lateinit var binding: ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_launcher
        )
        binding.splashTextview.setCharacterDelay(80)
        binding.splashTextview.setTextDisplayListener(this)
        binding.splashTextview.animateText(getString(R.string.welcome_to_school_enrollment_process))
    }

    override fun onTextComplete() {
        StudentProfileActivity.startActivity(this)
        finish()
    }
}