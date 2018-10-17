package com.example.sridharv.filestructureapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import android.content.Context.MODE_PRIVATE


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val isSDPresent = android.os.Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
        val isSDSupportedDevice = Environment.isExternalStorageRemovable()

        if (isSDSupportedDevice!! && isSDPresent) {
            // yes SD-card is present
            println("Sdcard")
            val mydir = this@MainActivity.getDir("users", Context.MODE_PRIVATE) //Creating an internal dir;
            if (!mydir.exists()) {
                mydir.mkdirs()
            }
        } else {
            val mydir = this@MainActivity.getDir("users", Context.MODE_PRIVATE) //Creating an internal dir;
            if (!mydir.exists()) {
                mydir.mkdirs()
            }
        }

    }
}
