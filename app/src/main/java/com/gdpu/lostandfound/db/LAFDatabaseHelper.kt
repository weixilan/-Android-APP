package com.gdpu.lostandfound.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class LAFDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
        private val createLost="create table Lost ("+
                "lost_id integer primary key autoincrement,"+
                "lost_name text ,"+
                "lost_details text,"+
                "lost_time text,"+
                "lost_address text,"+
                "lost_image text,"+
                "lost_phone text," +
                "user_id integer)"

    private val createFound="create table Found ("+
            "find_id integer primary key autoincrement,"+
            "find_name text ,"+
            "find_details text,"+
            "find_time text,"+
            "find_address text,"+
            "find_image text,"+
            "find_phone text," +
            "user_id integer)"

    private val createMessage="create table Message ("+
            "msg_id integer primary key autoincrement,"+
            "msg_name text ,"+
            "msg_details text,"+
            "msg_time text,"+
            "msg_image text," +
            "user_id integer)"

    private val createUser="create table User ("+
            "user_id integer primary key autoincrement ,"+
            "user_name text ,"+
            "user_pwd text,"+
            "user_sex text,"+
            "user_phone text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUser)
        db?.execSQL(createLost)
        db?.execSQL(createFound)
        db?.execSQL(createMessage)
        Toast.makeText(context,"create succeeded",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists User")
        db?.execSQL("drop table if exists Lost")
        db?.execSQL("drop table if exists Found")
        db?.execSQL("drop table if exists Message")
        onCreate(db)
    }
}