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
        val view: View = LayoutInflater.from(context).inflate(R.layout.listview_item, null)

        /* 위에서 생성된 view를 res-layout-main_lv_item.xml 파일의 각 View와 연결하는 과정이다. */
        val dogPhoto = view.findViewById<ImageView>(R.id.dogPhotoImg)
        val Item_id = view.findViewById<TextView>(R.id.item_id)
        val Item_location = view.findViewById<TextView>(R.id.item_location)
        val Item_rate = view.findViewById<TextView>(R.id.item_rate)
        val Item_view = view.findViewById<TextView>(R.id.item_view)
        val Item_tag = view.findViewById<TextView>(R.id.item_tag)

        val Data = DataList[p0]
        Item_id.text = Data.id
        Item_location.text = Data.location
        Item_rate.text = Data.rate.toString()
        Item_view.text = Data.view.toString()
        Item_tag.text = Data.tag






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

}