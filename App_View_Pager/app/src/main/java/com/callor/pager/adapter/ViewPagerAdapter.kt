package com.callor.pager.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.callor.pager.ui.dashboard.DashboardFragment
import com.callor.pager.ui.notifications.NotificationsFragment
import java.lang.IndexOutOfBoundsException

const val DASHBOARD_INDEX = 0;
const val NOTIFICATION_INDEX = 1;

class ViewPagerAdapter( fragment: Fragment) : FragmentStateAdapter(fragment) {

    // DashBoard, Notification fragment 를 Map type 으로 묶어서
    // pageTabList 에 담기
    private val pageTabList : Map<Int, ()->Fragment> =
        mapOf(
            DASHBOARD_INDEX to { DashboardFragment() },
            NOTIFICATION_INDEX to {NotificationsFragment() }
        )

    override fun getItemCount(): Int {
        return  pageTabList.size
    }
    override fun createFragment(position: Int): Fragment {
        return pageTabList[position]?.invoke() ?:
            throw IndexOutOfBoundsException()
    }
}