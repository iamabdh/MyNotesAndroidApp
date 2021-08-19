package com.example.mynotes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.widget.Toast
import java.util.*


val DB_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_TEXT = "text"
val COL_DATETIME = "datetime"
val COL_TIME = "time"
val COL_ID = "id"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEXT + " VARCHAR(256), " +
                COL_DATETIME + " VARCHAR(256), " +
                COL_TIME + " VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData (user : User){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TEXT, user.text)
        cv.put(COL_DATETIME, user.dateTime)
        cv.put(COL_TIME, user.time)
        var result = db.insert(TABLE_NAME, null, cv)

        if (result == -1.toLong()){
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
        }
    }


    fun readData() : MutableList<User> {
        val list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val  result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                val user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.text = result.getString(result.getColumnIndex(COL_TEXT))
                user.dateTime = result.getString(result.getColumnIndex(COL_DATETIME))
                user.time = result.getString(result.getColumnIndex(COL_TIME))
                list.add(user)
            } while(result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}