package com.kartik.test.schooluidemoproject.modules.profile

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.kartik.test.schooluidemoproject.utils.replaceFragment

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
        initNavigationViewListener()
    }

    fun setTitle(title: String) {
        binding.title.text = title
    }

    private fun addPersonalInformationFragment() {
        replaceFragment(PersonalInformationFragment.newInstance(), R.id.frame_container)
    }

    private fun addPhotoFragment() {
        replaceFragment(PhotoFragment.newInstance(), R.id.frame_container)
    }

    private fun addVideoFragment() {
        replaceFragment(VideoFragment.newInstance(), R.id.frame_container)
    }

    private fun addAdhaarCardFragment() {
        replaceFragment(AdhaarCardFragment.newInstance(), R.id.frame_container)
    }

    fun backPressed(@Suppress("UNUSED_PARAMETER") view: View) {
        finish()
    }

    fun nextButtonClick(@Suppress("UNUSED_PARAMETER") view: View) {
        openFragment(true)
    }

    private fun openFragment(isCallingFromNextButton: Boolean) {
        if (isCallingFromNextButton) {
            count++
        }
        animateBar(isCallingFromNextButton)
        val menu: Menu = binding.bottomNavigationView.menu
        when (count) {
            0 -> {
                addPersonalInformationFragment()
                menu.findItem(R.id.profile).isChecked = true
            }
            1 -> {
                addPhotoFragment()
                menu.findItem(R.id.photo).isChecked = true
            }
            2 -> {
                addVideoFragment()
                menu.findItem(R.id.video).isChecked = true
            }
            3 -> {
                addAdhaarCardFragment()
                menu.findItem(R.id.identity).isChecked = true
            }
            else -> {
                UsersListActivity.startActivity(this)
                finish()
            }
        }
    }

    fun animateBar(isCallingFromNextButton: Boolean) {
        val progressValue: Int = (count + 1) * progressSizeIncrease
        val anim = ObjectAnimator.ofArgb(
            binding.seekbar,
            "progress",
            binding.seekbar.progress,
            if (isCallingFromNextButton) {
                binding.seekbar.progress + progressSizeIncrease
            } else {
                progressValue
            }
        )
        anim.duration = 500
        anim.addUpdateListener { animation ->
            val animProgress = animation?.animatedValue
            binding.seekbar.progress = if (isCallingFromNextButton) {
                binding.seekbar.progress + animProgress.toString().toInt()
            } else {
                if (progressValue > binding.seekbar.progress) {
                    binding.seekbar.progress - animProgress.toString().toInt()
                } else {
                    binding.seekbar.progress + animProgress.toString().toInt()
                }
            }
        }
        anim.start()
    }

    override fun onBackPressed() {
        backPressed(binding.root)
    }

    private fun initNavigationViewListener() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.profile -> {
                    count = 0
                    openFragment(false)
                    true
                }
                R.id.photo -> {
                    count = 1
                    openFragment(false)
                    true
                }
                R.id.video -> {
                    count = 2
                    openFragment(false)
                    true
                }
                R.id.identity -> {
                    count = 3
                    openFragment(false)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}