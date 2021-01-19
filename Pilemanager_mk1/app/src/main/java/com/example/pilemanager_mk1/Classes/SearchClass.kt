package com.example.pilemanager_mk1.Classes

import android.database.Cursor
import com.example.pilemanager_mk1.db_class.DbOpenHelper

class SearchClass(private val mDbOpenHelper: DbOpenHelper) {
    //하나라도 걸리면
    fun SearchBy_contain(Keyword: String, SearchArray: ArrayList<String>): ArrayList<DataClass> {
        val iCursor: Cursor = mDbOpenHelper.selectColumns()
        var res:String = ""
        var al_datalist:ArrayList<DataClass> = arrayListOf<DataClass>()

        val map_data = mutableMapOf<String, String>()
        while(iCursor.moveToNext()){
            var _ID:String = iCursor.getString(iCursor.getColumnIndex("_id"));
            var ID:String = iCursor.getString(iCursor.getColumnIndex("id"));
            var LOCATION = iCursor.getString(iCursor.getColumnIndex("location"));
            var DATE = iCursor.getString(iCursor.getColumnIndex("date"));
            var LENGTH = iCursor.getString(iCursor.getColumnIndex("length"));
            var TAG = iCursor.getString(iCursor.getColumnIndex("tag"));
            var RATE = iCursor.getString(iCursor.getColumnIndex("rate"));
            var VIEW = iCursor.getString(iCursor.getColumnIndex("view"));
            var ETC = iCursor.getString(iCursor.getColumnIndex("etc"));
            map_data.put("_ID", _ID)
            map_data.put("ID", ID)
            map_data.put("LOCATION", LOCATION)
            map_data.put("DATE", DATE)
            map_data.put("LENGTH", LENGTH)
            map_data.put("TAG", TAG)
            map_data.put("RATE", RATE)
            map_data.put("VIEW", VIEW)
            map_data.put("ETC", ETC)

            var Data = DataClass(
                    _ID.toInt(),
                    ID,
                    LOCATION,
                    DATE,
                    LENGTH.toInt(),
                    TAG,
                    RATE.toInt(),
                    VIEW.toInt(),
                    ETC
            )

            if(Keyword == "TAG"){
                for(i in SearchArray) {
                    if(map_data["TAG"]!!.contains(i)){
                            al_datalist.add(Data)
                            break
                    }
                }

            }
            else{
                for(i in SearchArray){
                    if(map_data[Keyword] == i){
                            al_datalist.add(Data)
                            break
                    }
                }
            }
            map_data.clear()
        }
        al_datalist.distinct()
        return al_datalist
    }
    //모두 만족해야
    fun SearchBy(Keyword: String, SearchArray: ArrayList<String>): ArrayList<DataClass> {
        val iCursor: Cursor = mDbOpenHelper.selectColumns()
        var res:String = ""
        var al_datalist:ArrayList<DataClass> = arrayListOf<DataClass>()

        val map_data = mutableMapOf<String, String>()
        while(iCursor.moveToNext()){
            var _ID:String = iCursor.getString(iCursor.getColumnIndex("_id"));
            var ID:String = iCursor.getString(iCursor.getColumnIndex("id"));
            var LOCATION = iCursor.getString(iCursor.getColumnIndex("location"));
            var DATE = iCursor.getString(iCursor.getColumnIndex("date"));
            var LENGTH = iCursor.getString(iCursor.getColumnIndex("length"));
            var TAG = iCursor.getString(iCursor.getColumnIndex("tag"));
            var RATE = iCursor.getString(iCursor.getColumnIndex("rate"));
            var VIEW = iCursor.getString(iCursor.getColumnIndex("view"));
            var ETC = iCursor.getString(iCursor.getColumnIndex("etc"));
            map_data.put("_ID", _ID)
            map_data.put("ID", ID)
            map_data.put("LOCATION", LOCATION)
            map_data.put("DATE", DATE)
            map_data.put("LENGTH", LENGTH)
            map_data.put("TAG", TAG)
            map_data.put("RATE", RATE)
            map_data.put("VIEW", VIEW)
            map_data.put("ETC", ETC)

            var Data = DataClass(
                    _ID.toInt(),
                    ID,
                    LOCATION,
                    DATE,
                    LENGTH.toInt(),
                    TAG,
                    RATE.toInt(),
                    VIEW.toInt(),
                    ETC
            )

            if(Keyword == "TAG"){
                var flag:Boolean = true
                for(i in SearchArray) {
                    //태그를 모두 포함하고있지 않다면 삽입 X
                    //태그가 하나라도 포함이 안되어있으면 flag false 후 탈출
                    if(!(map_data["TAG"]!!.contains(i))){
                        flag = false
                        break
                    }
                }
                if(flag) al_datalist.add(Data)


            }
            else{
                for(i in SearchArray){
                    if(map_data[Keyword] == i){
                        al_datalist.add(Data)
                        break
                    }
                }
            }
            map_data.clear()
        }
        al_datalist.distinct()
        iCursor.close()
        return al_datalist
    }

    fun SearchAll(): ArrayList<DataClass> {
        val iCursor: Cursor = mDbOpenHelper.selectColumns()
        var res:String = ""
        var al_datalist:ArrayList<DataClass> = arrayListOf<DataClass>()

        val map_data = mutableMapOf<String, String>()
        while(iCursor.moveToNext()){
            var _ID:String = iCursor.getString(iCursor.getColumnIndex("_id"));
            var ID:String = iCursor.getString(iCursor.getColumnIndex("id"));
            var LOCATION = iCursor.getString(iCursor.getColumnIndex("location"));
            var DATE = iCursor.getString(iCursor.getColumnIndex("date"));
            var LENGTH = iCursor.getString(iCursor.getColumnIndex("length"));
            var TAG = iCursor.getString(iCursor.getColumnIndex("tag"));
            var RATE = iCursor.getString(iCursor.getColumnIndex("rate"));
            var VIEW = iCursor.getString(iCursor.getColumnIndex("view"));
            var ETC = iCursor.getString(iCursor.getColumnIndex("etc"));
            map_data.put("_ID", _ID)
            map_data.put("ID", ID)
            map_data.put("LOCATION", LOCATION)
            map_data.put("DATE", DATE)
            map_data.put("LENGTH", LENGTH)
            map_data.put("TAG", TAG)
            map_data.put("RATE", RATE)
            map_data.put("VIEW", VIEW)
            map_data.put("ETC", ETC)

            var Data = DataClass(
                    _ID.toInt(),
                    ID,
                    LOCATION,
                    DATE,
                    LENGTH.toInt(),
                    TAG,
                    RATE.toInt(),
                    VIEW.toInt(),
                    ETC
            )
            al_datalist.add(Data)

        }
        iCursor.close()
        return al_datalist
    }

    fun SearchTag(SearchArraylist: ArrayList<String>): ArrayList<DataClass>{
        val Searcharray: Array<String> = SearchArraylist.toArray(arrayOfNulls<String>(SearchArraylist.size))
        var cursor:Cursor = mDbOpenHelper.selectTag(Searcharray)
        var al_dataclass = arrayListOf<DataClass>()
        while (cursor.moveToNext()) {
            val _ID: Int = cursor.getInt(cursor.getColumnIndex("_id"))
            val ID: String = cursor.getString(cursor.getColumnIndex("id"))
            val LOCATION: String = cursor.getString(cursor.getColumnIndex("location"))
            val DATE: String = cursor.getString(cursor.getColumnIndex("date"))
            val LENGTH: Int = cursor.getInt(cursor.getColumnIndex("length"))
            val TAG: String = cursor.getString(cursor.getColumnIndex("tag"))
            val RATE: Int = cursor.getInt(cursor.getColumnIndex("rate"))
            val VIEW: Int = cursor.getInt(cursor.getColumnIndex("view"))
            val ETC: String = cursor.getString(cursor.getColumnIndex("etc"))
            al_dataclass.add(DataClass(_ID, ID, LOCATION, DATE, LENGTH, TAG, RATE, VIEW, ETC))
        }

        cursor.close()
        return al_dataclass
    }


}