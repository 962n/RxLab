package com.example.a962n.rxlab.presentation.userEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.a962n.rxlab.R
import com.example.a962n.rxlab.app
import kotlinx.android.synthetic.main.fragment_user_edit.*

class UserEditFragment : Fragment() {

    companion object {
        fun newInstance() = UserEditFragment()
    }


    private val viewModel: UserEditViewModel by viewModels {
        val app = this.context?.app() ?: throw Exception("")
        val scheduler = app.scheduler
        val database = app.db
        UserEditViewModel.Factory(scheduler, database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        buttonCountCheck.setOnClickListener {
            viewModel.checkUserCount()
        }
        viewModel.result.observe(viewLifecycleOwner, Observer<String> { str ->
            context?.apply {
                Toast.makeText(this, str, Toast.LENGTH_LONG).show()
            }
        })
    }


}