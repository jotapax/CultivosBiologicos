package com.example.cultivosbiologicos

import android.content.Context

class Permission(ctx : Context, val screen : String, val rol : Rol) {

    val access : Boolean
    val insert : Boolean
    val update : Boolean
    val delete : Boolean

    init {

        val db = UsersDB(ctx).readableDatabase

        val query = "SELECT * FROM ${UsersDB.TABLE_PERMISSION} WHERE " +
                " ${UsersDB.PERMISSION_ROL_NAME} = ? AND ${UsersDB.PERMISSION_SCREEN} = ?"

        val cursor = db.rawQuery(query, arrayOf(rol.name, screen))

        cursor.moveToFirst()

        this.access = cursor.getInt(cursor.getColumnIndex(UsersDB.PERMISSION_ACCESS)) == 1
        this.insert = cursor.getInt(cursor.getColumnIndex(UsersDB.PERMISSION_INSERT)) == 1
        this.update = cursor.getInt(cursor.getColumnIndex(UsersDB.PERMISSION_UPDATE)) == 1
        this.delete = cursor.getInt(cursor.getColumnIndex(UsersDB.PERMISSION_DELETE)) == 1

        cursor.close()
    }
}