package com.example.a962n.rxlab.presentation.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a962n.rxlab.R
import com.example.a962n.rxlab.app
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment() {

    private val adapter = UserListAdapter()

    companion object {
        fun newInstance() = UserListFragment()
    }

    private val viewModel: UserListViewModel by viewModels {
        val app = this.context?.app() ?: throw Exception("")
        val scheduler = app.scheduler
        val database = app.db
        UserListViewModel.Factory(scheduler, database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.users.observe(viewLifecycleOwner, Observer {
            adapter.refreshItems(it)
        })
        viewModel.refresh()
    }
}