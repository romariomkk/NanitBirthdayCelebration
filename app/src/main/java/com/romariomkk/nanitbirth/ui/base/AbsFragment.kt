package com.romariomkk.nanitbirth.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.romariomkk.nanitbirth.util.imgpick.ImagePickingDelegate
import java.io.File

abstract class AbsFragment<DB : ViewDataBinding, VM : AbsViewModel>
    : Fragment(), ImagePickingDelegate.Requester {

    protected lateinit var binding: DB
    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(vmClass)
            .apply { onAttached() }
    }

    abstract val layoutRes: Int
    abstract val vmClass: Class<VM>

    protected open val navController by lazy {
        findNavController()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }


    protected val imagePickingDelegate by lazy {
        ImagePickingDelegate<AbsFragment<*, *>>(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePickingDelegate.onActivityResult(requestCode, resultCode, data)
    }

    override fun onImagePicked(file: File) {}
    override fun onImageRequestCancel() {}

}