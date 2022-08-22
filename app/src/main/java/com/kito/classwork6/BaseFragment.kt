package com.kito.classwork6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.viewbinding.ViewBinding
typealias Inflater <I> = (LayoutInflater, ViewGroup?, Boolean) -> I
abstract class BaseFragment<VB : ViewBinding, VM: ViewModel>(
    private val inflate: Inflater<VB>,
    private val viewModelClass: Class<VM>,
    private val useSharedVm: Boolean
): Fragment () {

    protected val viewModel: VM by lazy{
        if (useSharedVm){
            ViewModelProvider(requireActivity())[viewModelClass]
        }
        else{
            ViewModelProvider(this)[viewModelClass]
        }
    }
    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStart()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun getStart()

}
