package com.example.sridharv.sqliteoneday

import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import db.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import model.Audio
import org.jetbrains.anko.toast
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addBtn.setOnClickListener(this)
        showBtn.setOnClickListener(this)
        deleteBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v) {
            addBtn -> {
                addData()
            }
            showBtn -> {
                showAll()
            }
            deleteBtn -> {
                deleteData(10)
            }
        }
    }

    private fun deleteData(i: Int) {
        var dbHelper = DBHelper(this);
        dbHelper.deleteTask(i)
        this.toast("value deleted")
    }

    private fun showAll() {
        var dbHelper = DBHelper(this);
        var audioList = ArrayList<Audio>();
        audioList = dbHelper.getTask() as ArrayList<Audio>
        println("TABLE_SIZE::: " + audioList.size)
        var strVal: String = "";
        try {
            for (audio in audioList) {
                var jsonData = JSONObject(audio.audioTemplate)
                println("audio_value" + jsonData.getString("widget"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun loadJSONFromAsset(): String {
        val file_name = "sample.json"
        val json_string = application.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        return json_string;
    }

    private fun addData() {
        var jsonData: String = "";
        var dbHelper = DBHelper(this);
        var audio = Audio();
        audio.audioTemplate = loadJSONFromAsset()
        audio.audioUrl = "https://google.com"
        audio.datePurchased = "10-10-2018"
        audio.identifier = "2"
        audio.inAppPurchased = "yes"
        audio.promoCode = "5423"
        audio.audioTitle = "dfssd"
        audio.updatedAt = "12-10-2018"
        dbHelper.addTask(audio)
        this.toast("value added to DB")
    }
}
