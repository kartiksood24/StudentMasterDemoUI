package com.kartik.test.schooluidemoproject.modules.profile.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kartik.test.schooluidemoproject.R
import com.kartik.test.schooluidemoproject.databinding.FragmentPhotoBinding
import com.kartik.test.schooluidemoproject.databinding.PickupOptionsBinding
import com.kartik.test.schooluidemoproject.modules.profile.StudentProfileActivity
import com.kartik.test.schooluidemoproject.utils.PermissionUtility
import com.kartik.test.schooluidemoproject.utils.UserAlertUtility


class PhotoFragment : Fragment() {
    companion object {
        fun newInstance(): PhotoFragment {
            return PhotoFragment()
        }
    }

    lateinit var binding: FragmentPhotoBinding
    var passpostImage = false
    var fullPicImage = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity is StudentProfileActivity) {
            (activity as? StudentProfileActivity)?.setTitle(getString(R.string.photo_information))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.choosepic.setOnClickListener {
            passpostImage = true
            fullPicImage = false
            chooseImageFromCameraAndGallery()
        }
        binding.fullPicImage.setOnClickListener {
            passpostImage = false
            fullPicImage = true
            chooseImageFromCameraAndGallery()
        }
    }

    private fun chooseImageFromCameraAndGallery() {
        val pickupOptionsBinding: PickupOptionsBinding? =
            UserAlertUtility.initCustomDialog(
                context,
                R.layout.pickup_options
            ) as? PickupOptionsBinding

        if (pickupOptionsBinding != null) {
            pickupOptionsBinding.camera.setOnClickListener {
                openCamera()
            }
            pickupOptionsBinding.Gallery.setOnClickListener {
                openGallery()
            }
            pickupOptionsBinding.close.setOnClickListener {
                UserAlertUtility.hideCustomDialog()
            }
            UserAlertUtility.showCustomDialog()
        }
    }


    private fun openCamera() {
        UserAlertUtility.hideCustomDialog()
        if (PermissionUtility.hasPermission(context, android.Manifest.permission.CAMERA)) {
            val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePicture, 0)
        } else {
            val permissions = ArrayList<String>()
            permissions.add(android.Manifest.permission.CAMERA)
            PermissionUtility.requestPermissions(this, permissions.toTypedArray(), 1001)
        }
    }

    private fun openGallery() {
        UserAlertUtility.hideCustomDialog()
        if (PermissionUtility.hasPermission(context, android.Manifest.permission.CAMERA)) {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, 1)
        } else {
            val permissions = ArrayList<String>()
            permissions.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            PermissionUtility.requestPermissions(this, permissions.toTypedArray(), 1002)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001) {
            openCamera()
        } else if (requestCode == 1002) {
            openGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data
                if (selectedImage != null) {
                    if (passpostImage) {
                        binding.passportImage.setImageURI(selectedImage)
                    } else {
                        binding.fullPicImage.setImageURI(selectedImage)
                    }
                } else {
                    val bitmap = data?.extras?.get("data") as? Bitmap
                    if (bitmap != null) {
                        if (passpostImage) {
                            binding.passportImage.setImageBitmap(bitmap)
                        } else {
                            binding.fullPicImage.setImageBitmap(bitmap)
                        }
                    }
                }
            }
            1 -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage: Uri? = data?.data
                if (passpostImage) {
                    binding.passportImage.setImageURI(selectedImage)
                } else {
                    binding.fullPicImage.setImageURI(selectedImage)
                }
            }
        }
    }
}