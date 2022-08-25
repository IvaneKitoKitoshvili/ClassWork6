package com.kito.classwork6.ui.homepage

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentHomePageBinding
import com.kito.classwork6.utils.PreferenceKeys
import kotlinx.coroutines.launch


class HomePageFragment :
    BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate) {

    private val args: HomePageFragmentArgs by navArgs()
    private val viewModel: HomePageViewModel by viewModels()

    override fun viewCreated() {

        init()

        onClickListeners()

        observers()

    }

    private fun init() {
        binding.tvToken.text = args.token
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPreferences().collect {
                if (it.contains(stringPreferencesKey(PreferenceKeys.TOKEN))){
                    binding.tvToken.text = it[stringPreferencesKey(PreferenceKeys.TOKEN)]
                }
            }
        }
    }

    private fun onClickListeners() {
        binding.btnLogOut.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.clear()
                // With Remove:
                // viewModel.remove(PreferenceKeys.TOKEN)
            }.invokeOnCompletion {
                findNavController().navigate(HomePageFragmentDirections.actionHomePageFragmentToLogInPageFragment())
            }
        }
    }
}