package com.example.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food.R
import com.example.food.bean.Browse

/**
 * Browse history adapter
 */
class BrowseAdapter(private val llEmpty: LinearLayout?, private val rvNewsList: RecyclerView) :
    RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {
    private val list: MutableList<Browse> = ArrayList()
    private var mActivity: Context? = null
    private var mItemListener: ItemListener? = null
    fun setItemListener(itemListener: ItemListener) {
        this.mItemListener = itemListener as ItemListener?
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        mActivity = viewGroup.context
        val view =
            LayoutInflater.from(mActivity).inflate(R.layout.item_rv_collect_list, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val browse = list[i]
        if (browse != null) {
            viewHolder.title.text = browse.title
            viewHolder.itemView.setOnClickListener {
                if (mItemListener != null) {
                    mItemListener!!.ItemClick(browse)
                }
            }
        }
    }

    fun addItem(listAdd: List<Browse>?) {
        //If loading the first page, clear the data list first
        list.clear()
        if (listAdd != null) {
            //add data
            list.addAll(listAdd)
        }
        //Notify RecyclerView to make changes - overall
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }

    interface ItemListener {
        fun ItemClick(browse: Browse?)
    }
}
