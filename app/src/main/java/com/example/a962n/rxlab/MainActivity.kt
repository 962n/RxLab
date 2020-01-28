package com.example.a962n.rxlab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val adapter by lazy {
        MainPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        tabLayout.addTab(tabLayout.newTab().setText("hoge"))
        tabLayout.addTab(tabLayout.newTab().setText("fuga"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: return
                viewPager.setCurrentItem(position, true)
            }
        })
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                tabLayout.setScrollPosition(position, positionOffset, true)
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
        viewPager.adapter = adapter
    }
}
