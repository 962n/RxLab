package com.example.a962n.rxlab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a962n.rxlab.presentation.userEdit.UserEditFragment
import com.example.a962n.rxlab.presentation.userList.UserListFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragments = listOf(
        UserListFragment(),
        UserEditFragment()
    )
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}