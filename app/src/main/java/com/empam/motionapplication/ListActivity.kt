package com.empam.motionapplication

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        class ItemHoleder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(position: Int) {
                (itemView as TextView).apply {

                    val fragment = when (position) {
                        0 -> {
                            text = "MainFragment"
                            MainFragment()
                        }
                        1 -> {
                            text = "MotionFragment"
                            MotionFragment()
                        }
                        2 -> {
                            text = "SwipeFragment"
                            SwipeFragment()
                        }
                        3 -> {
                            text = "VerticalSwipeFragment"
                            VSwipeFragment()
                        }
                        4 -> {
                            text = "TwoMotionsFragment"
                            TwoMotionsFragment()
                        }
                        5 -> {
                            text = "MFragment"
                            MFragment()
                        }
                        else -> throw IllegalStateException("No such item")
                    }

                    setOnClickListener {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment_container, fragment)
                            .addToBackStack(fragment::class.java.simpleName)
                            .commit()

                        list.visibility = View.GONE


                    }
                }
            }
        }

        list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = object : RecyclerView.Adapter<ItemHoleder>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHoleder =
                    ItemHoleder(layoutInflater.inflate(R.layout.item_motion, parent, false))

                override fun onBindViewHolder(holder: ItemHoleder, position: Int) = holder.bind(position)

                override fun getItemCount(): Int = 6
            }
        }
    }


    override fun onBackPressed() {


        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            else -> {
                supportFragmentManager.popBackStack()
                list.visibility = View.VISIBLE
            }
        }

    }


}

