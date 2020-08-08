package com.kartik.test.schooluidemoproject.modules.profile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.FragmentPersonalInformationBinding
import com.kartik.test.schooluidemoproject.modules.profile.StudentProfileActivity
import com.kartik.test.schooluidemoproject.utils.DatePickerUtility

class PersonalInformationFragment : Fragment(), DatePickerUtility.DatePickerInterface {
    companion object {
        fun newInstance(): PersonalInformationFragment {
            return PersonalInformationFragment()
        }
    }

    lateinit var binding: FragmentPersonalInformationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateofbirth.setOnClickListener {
            DatePickerUtility.showDatePicker(context, this)
        }
        binding.addProfileInformation.setOnClickListener {
            if (activity is StudentProfileActivity) {
                (activity as? StudentProfileActivity)?.nextButtonClick(binding.root)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (activity is StudentProfileActivity) {
            (activity as? StudentProfileActivity)?.setTitle(getString(R.string.profile_information))
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onDateGet(pDayOfMonth: Int, pYear: Int, pMonth: Int) {
        binding.dateofbirth.setText("$pDayOfMonth-$pMonth-$pYear")
    }
}