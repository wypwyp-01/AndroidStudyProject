package com.wyp.studyproject

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
class EventConflictActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_conflict_test)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = object : PagerAdapter() {

            override fun getCount(): Int = 3

            override fun isViewFromObject(view: View, obj: Any): Boolean {
                return view == obj
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any  {
                val view = layoutInflater.inflate(R.layout.conflict_test_page_item, container, false)
                val listView = view.findViewById<ListView>(R.id.listView)

                listView.adapter = object : BaseAdapter() {
                    override fun getCount() = 50
                    override fun getItem(position: Int) = null
                    override fun getItemId(position: Int) = position.toLong()

                    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val itemView = layoutInflater.inflate(R.layout.conflict_test_item_list, parent, false)
                        val tv = itemView.findViewById<TextView>(R.id.textView)
                        tv.text = "Page $position Item $position"
                        return itemView
                    }
                }

                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
                container.removeView(obj as View)
            }
        }
    }
}