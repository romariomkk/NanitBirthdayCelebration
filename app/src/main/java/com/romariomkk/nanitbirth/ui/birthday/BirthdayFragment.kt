package com.romariomkk.nanitbirth.ui.birthday

import android.os.Bundle
import android.view.View
import com.romariomkk.nanitbirth.R
import com.romariomkk.nanitbirth.databinding.FragmentBirthdayBinding
import com.romariomkk.nanitbirth.ui.base.AbsFragment
import kotlinx.android.synthetic.main.fragment_birthday.*


class BirthdayFragment : AbsFragment<FragmentBirthdayBinding, BirthdayViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_birthday
    override val vmClass: Class<BirthdayViewModel>
        get() = BirthdayViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClose.setOnClickListener {
            navController.popBackStack()
        }
        btnCapture.setOnClickListener {

        }
    }


}