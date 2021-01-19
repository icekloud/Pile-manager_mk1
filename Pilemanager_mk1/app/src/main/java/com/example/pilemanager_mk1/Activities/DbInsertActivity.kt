package com.example.pilemanager_mk1.Activities

import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.pilemanager_mk1.Classes.CustomAdapter
import com.example.pilemanager_mk1.R
import com.example.pilemanager_mk1.db_class.DbOpenHelper


class DbInsertActivity : AppCompatActivity() {
    var arrayEditText: ArrayList<EditText> = arrayListOf<EditText>()
    var Bt_update:Button? = null
    var al_tag:ArrayList<String> = arrayListOf<String>()
    var mDbOpenHelper = DbOpenHelper(this)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)


        mDbOpenHelper.open()
        mDbOpenHelper.create()


        val al_intent:ArrayList<ArrayList<String>> = arrayListOf<ArrayList<String>>()
        val al_search:ArrayList<String> = arrayListOf<String>()

        val al_edittext:ArrayList<EditText> = arrayListOf<EditText>()
        al_edittext.add(findViewById<EditText>(R.id.input_1))
        al_edittext.add(findViewById<EditText>(R.id.input_2))
        al_edittext.add(findViewById<EditText>(R.id.input_3))
        al_edittext.add(findViewById<EditText>(R.id.input_4))
        //al_edittext.add(findViewById<EditText>(R.id.input_5))
        al_edittext.add(findViewById<EditText>(R.id.input_6))
        al_edittext.add(findViewById<EditText>(R.id.input_7))
        al_edittext.add(findViewById<EditText>(R.id.input_8))
        Bt_update = findViewById(R.id.bt_update)

        var spinner_tag:Spinner = findViewById(R.id.sp_tag)
        //spinner_tag.setSelection(0)
        val adapter = CustomAdapter(
            this,  // Use our custom adapter
            android.R.layout.simple_spinner_item, SelectBy(mDbOpenHelper)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tag.setAdapter(adapter)

        spinner_tag.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                al_tag.clear()
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                CustomAdapter.flag = true
                var tag:String = "<" + spinner_tag.selectedItem as String + ">"
                al_tag.add(tag)

            }

        }


        Bt_update!!.setOnClickListener{
            var al_get:ArrayList<String> = arrayListOf<String>()
            var tag:String = ""

            for(i in 0..6){
                al_get.add(al_edittext[i].text.toString())
            }

            for(i in al_tag){
                if(!(i.equals("<>") or tag.contains(i))) tag += i
            }
            //var tag:String = "<" + spinner_tag.selectedItem as String + ">"
            mDbOpenHelper.insertColumn(al_get[0],al_get[1],al_get[2],al_get[3].toInt(),
                tag,al_get[4].toInt(), al_get[5].toInt(),al_get[6])

            var textview:TextView = findViewById(R.id.textView)
            textview.setText(tag)
        }






    }
    fun SelectBy(mDbOpenHelper: DbOpenHelper): Array<String> {
        val iCursor: Cursor = mDbOpenHelper.selecttagColumns()
        var arrayList:ArrayList<String> = arrayListOf<String>()
        arrayList.add("")

        while(iCursor.moveToNext()){
            var _ID:String = iCursor.getString(iCursor.getColumnIndex("_id"));
            var TAG_KIND:String = iCursor.getString(iCursor.getColumnIndex("tag_kind"));
            var TAG_CNT:String = iCursor.getString(iCursor.getColumnIndex("tag_cnt"));
            arrayList.add(TAG_KIND)
        }


        var array:Array<String> = arrayList.toArray(arrayOfNulls<String>(arrayList.size))
        iCursor.close()
        return array
    }

    override fun onDestroy() {
        super.onDestroy()
        //mDbOpenHelper.close()
    }

}

