package com.romariomkk.nanitbirth.ui.input

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.databinding.FragmentChildInputBinding
import com.romariomkk.nanitbirth.ui.base.AbsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_child_input.*
import org.joda.time.LocalDate
import java.io.File

@AndroidEntryPoint
class ChildInfoInputFragment : AbsFragment<FragmentChildInputBinding, ChildInfoInputViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_child_input
    override val vmClass: Class<ChildInfoInputViewModel>
        get() = ChildInfoInputViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel.observer)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        val now = LocalDate.now()
        dpBirth.minDate = now.minusYears(12).toDate().time
        dpBirth.maxDate = now.toDate().time

        btnPicker.setOnClickListener {
            imagePickingDelegate.openChooser()
        }

        btnCongrats.setOnClickListener {
            navController.navigate(ChildInfoInputFragmentDirections.toCongrats())
        }
    }

    override fun onImagePicked(file: File) {
        viewModel.updateChildImage(Uri.fromFile(file).toString())
    }

}