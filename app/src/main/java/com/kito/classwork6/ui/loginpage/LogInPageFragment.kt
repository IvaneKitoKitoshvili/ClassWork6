package com.kito.classwork6.ui.loginpage

import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentLogInPageBinding

class LogInPageFragment : BaseFragment<FragmentLogInPageBinding, LogInPageViewModel>(
    FragmentLogInPageBinding::inflate,
    LogInPageViewModel::class.java, false){
    override fun getStart() {
    }

}
