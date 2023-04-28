package com.krrishshx.worldofq.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerMyAdapter(list:ArrayList<Fragment>,fm:FragmentManager,lc:Lifecycle): FragmentStateAdapter(fm,lc) {
    val fragList:ArrayList<Fragment> = list
    override fun getItemCount(): Int = fragList.size
    override fun createFragment(position: Int): Fragment {
       return fragList[position]
    }
}