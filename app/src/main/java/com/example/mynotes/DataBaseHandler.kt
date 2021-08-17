package com.example.mynotes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.widget.Toast
import java.util.*


val DB_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_NAME = "name"
val COL_ID = "id"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertData (user : User){

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME, user.name)
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
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                list.add(user)
            } while(result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}