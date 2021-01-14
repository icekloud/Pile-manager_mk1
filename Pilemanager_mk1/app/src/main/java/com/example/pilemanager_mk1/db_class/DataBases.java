package com.example.pilemanager_mk1.db_class;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String ID = "id";
        public static final String LOCATION = "location";
        public static final String DATE = "date";
        public static final String LENGTH = "length";
        public static final String TAG = "tag";
        public static final String RATE = "rate";
        public static final String VIEW = "view";
        public static final String ETC = "etc";
        public static final String TAG_KIND = "tag_kind";
        public static final String TAG_CNT = "tag_cnt";



        public static final String _TABLENAME0 = "datatable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +ID+" text not null , "
                +LOCATION+" text not null , "
                +DATE+" text not null , "
                +LENGTH+" integer not null , "
                +TAG+" text , "
                +RATE+" integer not null , "
                +VIEW+" integer not null , "
                +ETC+" text );";


        public static final String _TABLENAME1 = "tagtable";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +TAG_KIND+" text not null , "
                +TAG_CNT+" integer not null ); ";

    }
}
