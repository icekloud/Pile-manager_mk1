package com.example.pilemanager_mk1.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pilemanager_mk1.*
import com.example.pilemanager_mk1.Classes.DataClass
import com.example.pilemanager_mk1.Classes.MainListAdapter
import com.example.pilemanager_mk1.Classes.SearchClass
import com.example.pilemanager_mk1.db_class.DbOpenHelper


class MainActivity : AppCompatActivity() {

    var db_view:TextView? = null
    var Res_view:TextView? = null
    var Button:Button? = null
    var Bt_insertactivity:Button? = null
    var Bt_inserttagactivity:Button? = null
    var Bt_reset:Button? = null
    var Bt_reset2:Button? = null
    var Lv_view:ListView? = null
    var Cond_1:EditText? = null
    var Cond_2:EditText? = null
    var Cond_3:EditText? = null

    var listAdapter: MainListAdapter? = null


    var arrayEditText: ArrayList<EditText> = arrayListOf<EditText>()
    var DataList = arrayListOf<DataClass>()

    var mDbOpenHelper = DbOpenHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbOpenHelper.open()
        mDbOpenHelper.create()


        //Res_view = findViewById<TextView>(R.id.res_view)
        //db_view = findViewById<TextView>(R.id.db_view)

        Button = findViewById<Button>(R.id.button)
        Bt_insertactivity = findViewById<Button>(R.id.bt_insertactivity)
        Bt_inserttagactivity = findViewById<Button>(R.id.bt_inserttagactivity)
        Bt_reset = findViewById<Button>(R.id.bt_reset)
        Bt_reset2 = findViewById<Button>(R.id.bt_reset2)
        Cond_1 = findViewById<EditText>(R.id.cond_1)
        Cond_2 = findViewById<EditText>(R.id.cond_2)
        Cond_3 = findViewById<EditText>(R.id.cond_3)

        //var intent:Intent = Intent(this, DbSearchActivity::class.java)
       // var al_search:String = intent
        Lv_view = findViewById(R.id.mainListView) as ListView







        //val al_searchlist = arrayListOf<String>()
        var searchClass: SearchClass =
            SearchClass(mDbOpenHelper)


        DataList = searchClass.SearchAll()
        //Log.d("al_Searchlist", al_searchlist.size.toString())
        listAdapter =
            MainListAdapter(this, DataList)
        Lv_view!!.adapter = listAdapter


        Button!!.setOnClickListener {
            var search_list: ArrayList<String> = arrayListOf<String>()
            if(Cond_1!!.text.length != 0)search_list.add(Cond_1!!.text.toString())
            if(Cond_2!!.text.length != 0)search_list.add(Cond_2!!.text.toString())
            if(Cond_3!!.text.length != 0)search_list.add(Cond_3!!.text.toString())

            DataList = searchClass.SearchTag(search_list)
            listAdapter =
                MainListAdapter(
                    this,
                    DataList
                )
            Lv_view!!.adapter = listAdapter
        }

        Bt_insertactivity!!.setOnClickListener{
            var intent: Intent = Intent(this,
                FileTest::class.java)
            startActivity(intent)
        }
        Bt_inserttagactivity!!.setOnClickListener{
            var intent: Intent = Intent(this,
                DbTagInsertActivity::class.java)
            startActivity(intent)
        }
        Bt_reset!!.setOnClickListener{
            mDbOpenHelper.deleteAllColumns_datatable()
        }
        Bt_reset2!!.setOnClickListener{
            mDbOpenHelper.deleteAllColumns_tagtable()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //mDbOpenHelper.close()

    }
}



