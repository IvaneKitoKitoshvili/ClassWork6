package com.kito.classwork6.ui.user

import com.kito.classwork6.BaseFragment
import com.kito.classwork6.databinding.FragmentUserBinding

class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(
    FragmentUserBinding::inflate,
    UserViewModel::class.java, false
) {
    override fun getStart() {
    }

}