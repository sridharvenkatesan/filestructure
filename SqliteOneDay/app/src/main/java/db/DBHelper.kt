package db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import model.Audio

/**
 * DBHelper class
 * It contains all the table structure such as Audio, Moment, Recipient, Resident , Story and Theme
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DBHelper.DB_NAME, null, DBHelper.DB_VERSION) {
    companion object {
        //DBVersion and name
        private const val DB_VERSION = 1
        private const val DB_NAME = "OneDayDB"
        //Audio Table Fields
        private const val AUDIO_TABLE = "Audio"
        private const val AUDIO_TABLE_ID = "audio_id"
        private const val AUDIO_TEMPLATE = "audio_template"
        private const val AUDIO_URL = "audio_url"
        private const val DATE_PURCHASED = "date_purchased"
        private const val IDENTIFIER = "identifier"
        private const val IN_APP_PURCHASED = "in_app_purchased"
        private const val PROMO_CODE = "promo_code"
        private const val TITLE = "audio_title"
        private const val UPDATED_AT = "updated_at"
        //Moment Table Fields
        private const val MOMENT_TABLE = "Moment"
        private const val MOMENT_TABLE_ID = "moment_id"
        private const val DATE_CAPTURED = "date_captured"
        private const val DATE_LAST_RENDERED = "date_last_rendered"
        private const val DATE_MOVIE_UPLOADED = "date_movie_uploaded"
        private const val DATE_THUMBNAIL_UPLOADED = "date_thumbnail_uploaded"
        private const val IS_SHARED = "is_shared"
        private const val MOMENT_IDENTIFIER = "moment_identifier"
        private const val MOMENT_LESS = "moment_less"
        private const val MOVIE_IDENTIFIER = "movie_identifier"
        private const val NAMEPLATE_TEXT = "nameplate_text"
        private const val ORDER = "order"
        private const val PROVISIONAL_MOVIE = "provisional_movie"
        private const val REMOTE_URL_STRING = "remote_url_string"
        private const val SECONDARY_NAMEPLATE = "secondary_nameplate"
        private const val SHARE_URL_STRING = "share_url_string"
        private const val SUMMARY = "summary"
        private const val MOMENT_TITLE = "moment_title"
        private const val UPLOAD_IDENTIFIER = "upload_identifier"
        // Recepient Table
//        private const val
        //creating audio table
        val CREATE_AUDIO_TABLE = "CREATE TABLE $AUDIO_TABLE (" +
                AUDIO_TABLE_ID + " INTEGER PRIMARY KEY," +
                AUDIO_TEMPLATE + " BLOB," + AUDIO_URL + " TEXT," +
                DATE_PURCHASED + " TEXT," + IDENTIFIER + " TEXT," +
                IN_APP_PURCHASED + " TEXT," + PROMO_CODE + " TEXT," +
                TITLE + " TEXT," + UPDATED_AT + " TEXT);"
        //creating moment table
        val CREATE_MOMENT_TABLE = "CREATE TABLE $MOMENT_TABLE (" +
                MOMENT_TABLE_ID + " INTEGER PRIMARY KEY," +
                DATE_CAPTURED + " BLOB," + DATE_LAST_RENDERED + " TEXT," +
                DATE_MOVIE_UPLOADED + " TEXT," + DATE_THUMBNAIL_UPLOADED + " TEXT," +
                IS_SHARED + " INTEGER," + MOMENT_IDENTIFIER + " TEXT," +
                MOMENT_LESS + " INTEGER," + MOVIE_IDENTIFIER + " TEXT," +
                NAMEPLATE_TEXT + " TEXT," + ORDER + " INTEGER," +
                PROVISIONAL_MOVIE + " TEXT," + REMOTE_URL_STRING + " TEXT," +
                SECONDARY_NAMEPLATE + " TEXT," + SHARE_URL_STRING + " TEXT," +
                SUMMARY + " TEXT," + MOMENT_TITLE + " TEXT," + UPLOAD_IDENTIFIER + " TEXT);"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_AUDIO_TABLE)
        db!!.execSQL(CREATE_MOMENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + AUDIO_TABLE);
        onCreate(db);
    }

    fun addTask(audio: Audio): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(AUDIO_TEMPLATE, audio.audioTemplate)
        values.put(AUDIO_URL, audio.audioUrl)
        values.put(DATE_PURCHASED, audio.datePurchased)
        values.put(IDENTIFIER, audio.identifier)
        values.put(IN_APP_PURCHASED, audio.inAppPurchased)
        values.put(PROMO_CODE, audio.promoCode)
        values.put(TITLE, audio.audioTitle)
        values.put(UPDATED_AT, audio.updatedAt)
        val _success = db.insert(AUDIO_TABLE, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    fun getTask(): List<Audio> {
        val audioList = ArrayList<Audio>();
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $AUDIO_TABLE"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                val audio = Audio()
                audio.audioTemplate = cursor.getString(cursor.getColumnIndex(AUDIO_TEMPLATE))
                audio.audioUrl = cursor.getString(cursor.getColumnIndex(AUDIO_URL))
                audio.datePurchased = cursor.getString(cursor.getColumnIndex(DATE_PURCHASED))
                audio.identifier = cursor.getString(cursor.getColumnIndex(IDENTIFIER))
                audio.inAppPurchased = cursor.getString(cursor.getColumnIndex(IN_APP_PURCHASED))
                audio.promoCode = cursor.getString(cursor.getColumnIndex(PROMO_CODE))
                audio.audioTitle = cursor.getString(cursor.getColumnIndex(TITLE))
                audio.updatedAt = cursor.getString(cursor.getColumnIndex(UPDATED_AT))
                audioList.add(audio)
            }
        }
        cursor.close()
        return audioList
    }

    fun deleteTask(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(AUDIO_TABLE, AUDIO_TABLE_ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

}