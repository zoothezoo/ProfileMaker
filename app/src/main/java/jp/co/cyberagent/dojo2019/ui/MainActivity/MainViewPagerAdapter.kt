package jp.co.cyberagent.dojo2019.ui.MainActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import jp.co.cyberagent.dojo2019.ui.MainActivity.friendsList.FriendsFragment
import jp.co.cyberagent.dojo2019.ui.MainActivity.inputAccount.inputAccountFragment

class MainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){


    override fun getItem(position: Int): Fragment {
        val friendsfragment = FriendsFragment()
        val inputfragment = inputAccountFragment()

        return when (position) {
            0 -> inputfragment
            else -> friendsfragment
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "<"
            else -> ">"
        }
    }

    override fun getItemPosition(o: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

}



