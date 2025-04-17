package com.gdpu.lostandfound.bean

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Bean(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {
        private val createLost="create table Lost ("+
                "lost_id integer primary key autoincremrnt,"+
                "lost_name text ,"+
                "lost_details text,"+
                "lost_time text,"+
                "lost_image text,"+
                "lost_phone text)"

    private val createFound="create table Found ("+
            "find_id integer primary key autoincremrnt,"+
            "find_name text ,"+
            "find_details text,"+
            "find_time text,"+
            "find_image text,"+
            "find_phone text)"

    private val createUser="create table User ("+
            "user_id integer primary key autoincremrnt,"+
            "user_name text ,"+
            "user_password text,"+
            "user_sex text,"+
            "user_phone text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUser)
        db?.execSQL(createLost)
        db?.execSQL(createFound)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}