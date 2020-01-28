package com.example.a962n.rxlab.presentation.userEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a962n.rxlab.R
import com.example.a962n.rxlab.app

class UserEditFragment : Fragment() {

    companion object {
        fun newInstance() = UserEditFragment()
    }


    private val viewModel:UserEditViewModel by viewModels {
        val scheduler = this.context?.app()?.scheduler ?: throw Exception("")
        UserEditViewModel.Factory(scheduler)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_edit, container, false)
    }


}