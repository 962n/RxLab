package com.example.a962n.rxlab.presentation.userEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.a962n.rxlab.R
import com.example.a962n.rxlab.app
import kotlinx.android.synthetic.main.fragment_user_edit.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        buttonAdd1.setOnClickListener {
            viewModel.addRandomUser()
        }
        buttonAdd10.setOnClickListener {
            viewModel.addRandomUser10()
        }
        buttonDelete.setOnClickListener {
            viewModel.deleteRandomUser()
        }
        buttonDeleteAll.setOnClickListener {
            viewModel.deleteAllUser()
        }
    }


}