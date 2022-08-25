package com.kito.classwork6.ui.registerpage

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentRegisterPageBinding
import com.kito.classwork6.models.Request
import com.kito.classwork6.utils.FragmentResUtils
import com.kito.classwork6.utils.ResponseHandler
import kotlinx.coroutines.launch

class RegisterPageFragment :
    BaseFragment<FragmentRegisterPageBinding>(FragmentRegisterPageBinding::inflate) {

    private val viewModel: RegisterPageViewModel by viewModels()

    override fun viewCreated() {

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btnReg.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getRegisterFlow(getUserInfo()).collect {
                    when (it) {
                        is ResponseHandler.Success<*> -> {
                            buildFragmentResult()
                            findNavController().navigate(RegisterPageFragmentDirections.actionRegisterPageFragmentToLogInPageFragment())
                        }
                        is ResponseHandler.Error -> {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is ResponseHandler.Loader -> {
                            binding.progressBar.isVisible = it.isLoading
                        }
                    }
                }
            }
        }
    }

    private fun buildFragmentResult() {
        setFragmentResult(
            requestKey = FragmentResUtils.AUTH_KEY,
            result = bundleOf(
                FragmentResUtils.EMAIL to binding.etEmail.text.toString(),
                FragmentResUtils.PASSWORD to binding.etPass.text.toString()
            )
        )
    }

    private fun getUserInfo() = Request(
        binding.etEmail.text.toString(),
        binding.etPass.text.toString()
    )

}