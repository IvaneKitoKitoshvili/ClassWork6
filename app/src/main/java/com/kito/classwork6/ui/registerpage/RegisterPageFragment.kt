package com.kito.classwork6.ui.registerpage

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentRegisterPageBinding
import com.kito.classwork6.retrofitclient.ResultHendler
import com.kito.homework15.ui.registerpage.RegisterPageFragmentDirections
import kotlinx.coroutines.launch

class RegisterPageFragment : BaseFragment<FragmentRegisterPageBinding, RegisterPageViewModel>(
    FragmentRegisterPageBinding::inflate,
    RegisterPageViewModel::class.java, false
) {

    private fun observe() {
        binding.let {

            with(it) {
                when {
                    etEmail.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Email field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    etPass.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Password field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    etUsername.text.toString().isEmpty() -> {
                        Toast.makeText(
                            requireContext(),
                            "Username field must not be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        viewLifecycleOwner.lifecycleScope.launch {
                            repeatOnLifecycle(Lifecycle.State.STARTED) {
                                viewModel.register(
                                    etEmail.text.toString(),
                                    etPass.text.toString()
                                ).collect {
                                    when (it) {
                                        is ResultHendler.Success -> {
                                            register()
                                        }
                                        is ResultHendler.Error -> {
                                            Toast.makeText(
                                                requireContext(),
                                                it.errorMSg,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun register() {
        findNavController().navigate(
            RegisterPageFragmentDirections.actionRegisterPageFragmentToUserFragment()
        )
    }

    override fun getStart() {
        binding.btnReg.setOnClickListener {
            //observe()
            register()
        }
    }
}
