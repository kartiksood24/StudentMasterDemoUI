package com.kartik.test.schooluidemoproject.modules.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.FragmentVideoBinding
import com.kartik.test.schooluidemoproject.modules.profile.StudentProfileActivity

class VideoFragment : Fragment() {
    companion object {
        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }

    lateinit var binding: FragmentVideoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity is StudentProfileActivity) {
            (activity as? StudentProfileActivity)?.setTitle(getString(R.string.video_information))
        }
    }
}