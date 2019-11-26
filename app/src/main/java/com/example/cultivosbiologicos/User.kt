package com.example.cultivosbiologicos

import android.content.Context
import android.util.Log
import java.lang.Exception

class User(ctx : Context, val username: String) {

    val password : String
    val rol : Rol

    init {

        val db = UsersDB(ctx).readableDatabase

        val query = "SELECT * FROM ${UsersDB.TABLE_USER} WHERE ${UsersDB.USER_NAME} =  ?"

        val cursor = db.rawQuery(query, arrayOf(username))

        if (cursor.count < 1) {

            throw Exception("User not found")

        } else {

            cursor.moveToFirst()

            this.password = cursor.getString(cursor.getColumnIndex(UsersDB.USER_PASSWORD))
            val rolName =  cursor.getString(cursor.getColumnIndex(UsersDB.USER_ROL_NAME))
            this.rol = Rol(ctx, rolName)
            cursor.close()
        }


    }
}