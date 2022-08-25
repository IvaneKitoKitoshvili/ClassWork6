package com.kito.classwork6.ui.loginpage

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentLogInPageBinding
import com.kito.classwork6.models.LogIn
import com.kito.classwork6.models.Request
import com.kito.classwork6.utils.FragmentResUtils
import com.kito.classwork6.utils.FragmentResUtils.AUTH_KEY
import com.kito.classwork6.utils.PreferenceKeys
import com.kito.classwork6.utils.ResponseHandler
import kotlinx.coroutines.launch

class LogInPageFragment :
    BaseFragment<FragmentLogInPageBinding>(FragmentLogInPageBinding::inflate) {

    private val viewModel: LogInPageViewModel by viewModels()

    override fun viewCreated() {

        checkSession()

        fragmentResultListener()

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btnReg.setOnClickListener {
            findNavController().navigate(LogInPageFragmentDirections.actionLogInPageFragmentToRegisterPageFragment())
        }
        binding.btnLogInOn.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getLoginFlow(getUserInfo()).collect {
                    when (it) {
                        is ResponseHandler.Success<*> -> {
                            if (binding.cbRemember.isChecked) {
                                viewModel.save(
                                    PreferenceKeys.TOKEN,
                                    (it.result as LogIn).token ?: ""
                                )
                            } else {
                                findNavController().navigate(
                                    LogInPageFragmentDirections.actionLogInPageFragmentToHomePageFragment(
                                        token = (it.result as LogIn).token ?: ""
                                    )
                                )
                            }
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

    private fun checkSession() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPreferences().collect {
                if (it.contains(stringPreferencesKey(PreferenceKeys.TOKEN))) {
                    findNavController().navigate(
                        LogInPageFragmentDirections.actionLogInPageFragmentToHomePageFragment(
                            token = it[stringPreferencesKey(PreferenceKeys.TOKEN)] ?: "No Data"
                        )
                    )
                }
            }
        }
    }

    private fun fragmentResultListener() {
        setFragmentResultListener(AUTH_KEY) { _, bundle ->
            binding.etUsername.setText(bundle.getString(FragmentResUtils.EMAIL, "No Value"))
            binding.etPassword.setText(bundle.getString(FragmentResUtils.PASSWORD, "No Value"))
        }
    }

    private fun getUserInfo() = Request(
        binding.etUsername.text.toString(),
        binding.etPassword.text.toString()
    )

}
