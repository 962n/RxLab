package com.example.a962n.rxlab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a962n.rxlab.ui.main.MainFragment

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {



    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return MainFragment.newInstance()
    }
}