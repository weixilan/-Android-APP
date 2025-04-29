package com.gdpu.lostandfound.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gdpu.lostandfound.R
import com.gdpu.lostandfound.fragment.AddFragment
import com.google.android.material.tabs.TabLayout

class AddActivity : AppCompatActivity() {
    private val goodsTypeList = listOf("添加失物", "添加招领")
    private val fragmentList = ArrayList<AddFragment>()

    private lateinit var sharedpreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        sharedpreferences = getApplication().getSharedPreferences("User", Context.MODE_PRIVATE);
        val user_id = sharedpreferences.getString("user_id", "");

        val tableLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        for (goodsType in goodsTypeList) {
            fragmentList.add(AddFragment(goodsType))
//            val fragment=AddFragment(goodsType)
//            val bundle :Bundle= TODO()
//            bundle.putString("user_id",user_id)
//            fragment.arguments=bundle
        }

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        tableLayout.setupWithViewPager(viewPager)

    }


    // BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ----> Indicates that only the current fragment will be in the {@link Lifecycle.State#RESUMED}
    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        // 展示顶部导航栏标题
        override fun getPageTitle(position: Int): CharSequence? {
            return goodsTypeList[position]
        }

    }

}