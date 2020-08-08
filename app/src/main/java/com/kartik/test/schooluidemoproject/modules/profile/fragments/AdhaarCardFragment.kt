package com.kartik.test.schooluidemoproject.modules.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.FragmentAdhaarCardBinding
import com.kartik.test.schooluidemoproject.modules.profile.StudentProfileActivity

class AdhaarCardFragment : Fragment() {
    companion object {
        fun newInstance(): AdhaarCardFragment {
            return AdhaarCardFragment()
        }
    }

    lateinit var binding: FragmentAdhaarCardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdhaarCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity is StudentProfileActivity) {
            (activity as? StudentProfileActivity)?.setTitle(getString(R.string.adhaar_card_information))
        }
    }
}