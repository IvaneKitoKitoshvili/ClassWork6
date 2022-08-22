package com.kito.homework15.ui.welcomepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kito.classwork6.databinding.FragmentWelcomePageBinding


class WelcomePageFragment : Fragment() {

    private var binding: FragmentWelcomePageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun clickListener() {
        binding?.btnLogIn?.setOnClickListener {
            findNavController().navigate(WelcomePageFragmentDirections.actionWelcomePageFragmentToLogInPageFragment())
        }
        binding?.btnRegister?.setOnClickListener {
            findNavController().navigate(WelcomePageFragmentDirections.actionWelcomePageFragmentToRegisterPageFragment())
        }
    }
}