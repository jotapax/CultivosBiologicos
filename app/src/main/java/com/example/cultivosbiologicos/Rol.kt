package com.example.cultivosbiologicos

import android.content.Context

class Rol(ctx : Context, val name : String) {

    companion object {
        val ADMIN = "Admin"
        val USER = "User"
        val GUEST = "Guest"
    }

    val rolDescription : String
    val isAdmin : Boolean

    init {

        val db = UsersDB(ctx).readableDatabase

        val query = "SELECT * FROM ${UsersDB.TABLE_ROL} WHERE ${UsersDB.ROL_NAME} = ?"

        val cursor = db.rawQuery(query, arrayOf(name))

        cursor.moveToFirst()

        this.rolDescription = cursor.getString(cursor.getColumnIndex(UsersDB.ROL_DESC))
        this.isAdmin = cursor.getInt(cursor.getColumnIndex(UsersDB.IS_ADMIN)) == 1

        cursor.close()
    }
}