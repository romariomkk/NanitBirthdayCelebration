package com.romariomkk.nanitbirth.ui.birthday

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.databinding.FragmentBirthdayBinding
import com.romariomkk.nanitbirth.ui.base.AbsFragment
import com.romariomkk.nanitbirth.util.ShareUtil.shareImage
import com.romariomkk.nanitbirth.util.ext.takeScreenShot
import kotlinx.android.synthetic.main.fragment_birthday.*
import java.io.File


class BirthdayFragment : AbsFragment<FragmentBirthdayBinding, BirthdayViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_birthday
    override val vmClass: Class<BirthdayViewModel>
        get() = BirthdayViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        btnClose.setOnClickListener {
            navController.popBackStack()
        }
        btnCapture.setOnClickListener {
            imagePickingDelegate.openChooser()
        }
        tvShare.setOnClickListener {
            clContent.takeScreenShot()?.let { path -> shareImage(path) }
        }
    }

    override fun onImagePicked(file: File) {
        viewModel.updateChildImage(Uri.fromFile(file).toString())
    }

}