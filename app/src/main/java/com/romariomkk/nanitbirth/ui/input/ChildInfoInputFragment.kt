package com.romariomkk.nanitbirth.ui.input

import android.os.Bundle
import android.view.View
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.databinding.FragmentChildInputBinding
import com.romariomkk.nanitbirth.ui.base.AbsFragment
import kotlinx.android.synthetic.main.fragment_child_input.*
import org.joda.time.LocalDate

class ChildInfoInputFragment : AbsFragment<FragmentChildInputBinding, ChildInfoInputViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_child_input

    override val vmClass: Class<ChildInfoInputViewModel>
        get() = ChildInfoInputViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val now = LocalDate.now()
        dpBirth.minDate = now.minusYears(12).toDate().time
        dpBirth.maxDate = now.toDate().time
        dpBirth.init(now.year, now.monthOfYear, now.dayOfMonth) { _, year, monthOfYear, dayOfMonth ->
            viewModel.birthDate.value = LocalDate(year, monthOfYear, dayOfMonth).toDate()
        }

    }

}