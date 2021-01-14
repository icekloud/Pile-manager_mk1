package com.example.pilemanager_mk1.db_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
            db.execSQL(DataBases.CreateDB._CREATE1);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME1);

            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }


    public long insertColumn(String id, String location, String date, int length, String tag, int rate, int view, String etc){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.ID, id);
        values.put(DataBases.CreateDB.LOCATION, location);
        values.put(DataBases.CreateDB.DATE, date);
        values.put(DataBases.CreateDB.LENGTH, length);
        values.put(DataBases.CreateDB.TAG, tag);
        values.put(DataBases.CreateDB.RATE, rate);
        values.put(DataBases.CreateDB.VIEW, view);
        values.put(DataBases.CreateDB.ETC, etc);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }
    public long inserttagColumn(String tag){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TAG_KIND, tag);
        values.put(DataBases.CreateDB.TAG_CNT, 0);

        return mDB.insert(DataBases.CreateDB._TABLENAME1, null, values);
    }

    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }
    public Cursor selecttagColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME1, null, null, null, null, null, null);
    }
    public void deleteAllColumns_datatable() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }
    public void deleteAllColumns_tagtable() {
        mDB.delete(DataBases.CreateDB._TABLENAME1, null, null);
    }
    public boolean deleteColumn_datatable(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }


}