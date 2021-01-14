package com.example.pilemanager_mk1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/* MainListAdapter.kt */

class MainListAdapter (val context: Context, val DataList: ArrayList<DataClass>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        val view: View
        val holder:ViewHolder

        if(p1 == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null)
            /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */
            holder = ViewHolder()
            holder.dogPhoto = view.findViewById<ImageView>(R.id.dogPhotoImg)
            holder.Item_id = view.findViewById<TextView>(R.id.item_id)
            holder.Item_location = view.findViewById<TextView>(R.id.item_location)
            holder.Item_rate = view.findViewById<TextView>(R.id.item_rate)
            holder.Item_view = view.findViewById<TextView>(R.id.item_view)
            holder.Item_tag = view.findViewById<TextView>(R.id.item_tag)
            view.tag = holder

        } else {
            holder = p1.tag as ViewHolder
            view = p1
        }

        val Data = DataList[p0]
        holder.Item_id?.text = Data.id
        holder.Item_location?.text = Data.location
        holder.Item_rate?.text = Data.rate.toString()
        holder.Item_view?.text = Data.view.toString()
        holder.Item_tag?.text = Data.tag






        return view
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
        return DataList[p0]

    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return DataList.size
    }
    private class ViewHolder {
        var dogPhoto : ImageView? = null
        var Item_id:TextView? = null
        var Item_location:TextView? = null
        var Item_rate:TextView? = null
        var Item_view:TextView? = null
        var Item_tag:TextView? = null
    }

}