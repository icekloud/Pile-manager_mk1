package com.example.pilemanager_mk1.Activities

import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pilemanager_mk1.Classes.SearchClass
import com.example.pilemanager_mk1.R
import com.example.pilemanager_mk1.db_class.DbOpenHelper
import java.io.File
import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.net.URLEncoder



class FileTest : AppCompatActivity() {

    var textview:TextView? = null
    var mDbOpenHelper = DbOpenHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filetest)

        textview = findViewById(R.id.textView2)


        val Top_Path = "/sdcard"//Environment.getExternalStorageDirectory().toString()
        var Path = Top_Path + "/Download"
        textview!!.text = Path

        val folder: File = File(Path)

        val context = applicationContext


        var files: Array<File> = folder.listFiles()

        mDbOpenHelper.open()
        mDbOpenHelper.create()

        val retriever: MediaMetadataRetriever = MediaMetadataRetriever()

        if (folder.isDirectory && folder.exists()) {
            var files: Array<File> = folder.listFiles()

            if (files != null) {
                for (i in files) {
                    Log.d("exists :",i.path + mDbOpenHelper.existLOCATION(i.path).toString())

                        if (!mDbOpenHelper.existLOCATION(i.path) && i.isFile && i.name.endsWith(".mp4")) {
                            try {
                            Log.d("currentfile : ", i.name)
                            retriever.setDataSource(i.path)

                            var time: String? =
                                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                            var timeInSec: Int = (time!!.toInt() / 1000).toInt()

                            val id: String = i.name
                            val location: String = i.path
                            val date: String = "date"
                            val length: Int = timeInSec
                            val tag: String = "<여행><철권>"
                            val rate: Int = -1
                            val view: Int = 0
                            val etc: String = ""
                                mDbOpenHelper.insertColumn(
                                        id,
                                        location,
                                        date,
                                        length,
                                        tag,
                                        rate,
                                        view,
                                        etc
                                )

                        }catch (e: RuntimeException) {
                                val id: String = i.name
                                val location: String = i.path
                                val date: String = "date"
                                val length: Int = 0
                                val tag: String = "<여행><철권>"
                                val rate: Int = -1
                                val view: Int = 0
                                val etc: String = ""
                                    mDbOpenHelper.insertColumn(
                                        id,
                                        location,
                                        date,
                                        length,
                                        tag,
                                        rate,
                                        view,
                                        etc
                                    )

                                }

                            }
                    }
                }
                retriever.release()

            }

            //retriever.release()
        }

    override fun onDestroy() {
        super.onDestroy()
        //mDbOpenHelper.close()
    }


    }



