package com.kartik.test.schooluidemoproject.modules.profile

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.ActivityStudentProfileBinding
import com.kartik.test.schooluidemoproject.modules.profile.fragments.AdhaarCardFragment
import com.kartik.test.schooluidemoproject.modules.profile.fragments.PersonalInformationFragment
import com.kartik.test.schooluidemoproject.modules.profile.fragments.PhotoFragment
import com.kartik.test.schooluidemoproject.modules.profile.fragments.VideoFragment
import com.kartik.test.schooluidemoproject.modules.user.UsersListActivity
import com.kartik.test.schooluidemoproject.utils.addFragment
import com.kartik.test.schooluidemoproject.utils.getCurrentFragment
import com.kartik.test.schooluidemoproject.utils.getPreviousFragment
import com.kartik.test.schooluidemoproject.utils.popBackStack

class StudentProfileActivity : AppCompatActivity() {
    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, StudentProfileActivity::class.java))
        }
    }

    lateinit var binding: ActivityStudentProfileBinding
    private val fragmentCount: Int = 4
    private var count: Int = 0
    private var progressSizeIncrease: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_profile)
        binding.seekbar.isEnabled = false
        progressSizeIncrease = binding.seekbar.max / fragmentCount
        binding.seekbar.progress = progressSizeIncrease
        addPersonalInformationFragment()

    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    private fun addPersonalInformationFragment() {
        addFragment(PersonalInformationFragment.newInstance(), R.id.frame_container, true)
    }

    fun addPhotoFragment() {
        addFragment(PhotoFragment.newInstance(), R.id.frame_container, true)
    }

    private fun addVideoFragment() {
        addFragment(VideoFragment.newInstance(), R.id.frame_container, true)
    }

    private fun addAdhaarCardFragment() {
        addFragment(AdhaarCardFragment.newInstance(), R.id.frame_container, true)
    }

    fun backPressed(@Suppress("UNUSED_PARAMETER") view: View) {
        val getCurrentFragment = getCurrentFragment()
        if (getCurrentFragment != null && getCurrentFragment is PersonalInformationFragment) {
            finish()
        } else if (getCurrentFragment != null) {
            popBackStack()
            getPreviousFragment()?.onResume()
            count--
            animateBar(false)
        } else {
            finish()
        }
    }

    fun nextButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        count++
        animateBar(true)
        when (count) {
            0 -> {
                addPersonalInformationFragment()
            }
            1 -> {
                addPhotoFragment()
            }
            2 -> {
                addVideoFragment()
            }
            3 -> {
                addAdhaarCardFragment()
            }
            else -> {
                UsersListActivity.startActivity(this)
                finish()
            }
        }
    }

    fun animateBar(isIncreasing: Boolean) {
        val anim = ObjectAnimator.ofArgb(
            binding.seekbar,
            "progress",
            binding.seekbar.progress,
            if (isIncreasing) binding.seekbar.progress + progressSizeIncrease else binding.seekbar.progress - progressSizeIncrease
        )
        anim.duration = 500
        anim.addUpdateListener { animation ->
            val animProgress = animation?.animatedValue
            binding.seekbar.progress = if (isIncreasing) {
                binding.seekbar.progress + animProgress.toString().toInt()
            } else {
                binding.seekbar.progress - animProgress.toString().toInt()
            }
        }
        anim.start()
    }

    override fun onBackPressed() {
        backPressed(binding.root)
    }
}