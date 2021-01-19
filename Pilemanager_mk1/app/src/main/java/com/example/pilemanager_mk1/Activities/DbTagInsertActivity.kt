package com.example.pilemanager_mk1.Activities

import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.pilemanager_mk1.R
import com.example.pilemanager_mk1.db_class.DbOpenHelper

class DbTagInsertActivity : AppCompatActivity() {
    var arrayEditText: ArrayList<EditText> = arrayListOf<EditText>()
    var Bt_update:Button? = null
    var Input_1:EditText? = null
    var mDbOpenHelper = DbOpenHelper(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserttag)


        mDbOpenHelper.open()
        mDbOpenHelper.create()

        Input_1 = findViewById<EditText>(R.id.input_1)
        Bt_update = findViewById<Button>(R.id.bt_update)


        Bt_update!!.setOnClickListener{
            var inp_tag:String = Input_1!!.text.toString()
            if(inp_tag.length != 0){
                SelectBy(
                    mDbOpenHelper,
                    inp_tag
                )
            }
        }



        }

    fun SelectBy(mDbOpenHelper: DbOpenHelper,Tag:String) {
        val iCursor: Cursor = mDbOpenHelper.selecttagColumns()
        var res:String = ""
        var cnt:Int = 0

        while(iCursor.moveToNext()){
            var _ID:String = iCursor.getString(iCursor.getColumnIndex("_id"));
            var TAG_KIND:String = iCursor.getString(iCursor.getColumnIndex("tag_kind"));
            var TAG_CNT:String = iCursor.getString(iCursor.getColumnIndex("tag_cnt"));

            if(TAG_KIND.equals(Tag)){
                cnt += 1
                break
            }

        }
        if(cnt == 0) mDbOpenHelper.inserttagColumn(Tag)


    }

    override fun onDestroy() {
        super.onDestroy()
        //mDbOpenHelper.close()
    }
}
