package com.romariomkk.nanitbirth.ui.birthday

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.databinding.FragmentBirthdayBinding
import com.romariomkk.nanitbirth.ui.base.AbsFragment
import com.romariomkk.nanitbirth.util.ShareUtil.shareImage
import com.romariomkk.nanitbirth.util.ext.clearStatusBarColor
import com.romariomkk.nanitbirth.util.ext.setStatusBarColor
import com.romariomkk.nanitbirth.util.ext.takeScreenShot
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_birthday.*
import java.io.File

@AndroidEntryPoint
class BirthdayFragment : AbsFragment<FragmentBirthdayBinding, BirthdayViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_birthday
    override val vmClass: Class<BirthdayViewModel>
        get() = BirthdayViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

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

    override fun onStart() {
        super.onStart()
        viewModel.uiMode.observe(viewLifecycleOwner, Observer { uiMode ->
            requireActivity().setStatusBarColor(
                when (uiMode) {
                    is BirthdayUiState.Fox -> R.color.bg_light_fox
                    is BirthdayUiState.Pelican -> R.color.bg_light_pelican
                    is BirthdayUiState.Elephant -> R.color.bg_light_elephant
                }
            )
        })
    }

    override fun onStop() {
        super.onStop()
        requireActivity().clearStatusBarColor()
    }

}