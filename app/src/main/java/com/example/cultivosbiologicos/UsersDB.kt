package com.example.cultivosbiologicos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UsersDB(ctx : Context) : SQLiteOpenHelper(ctx, "Users", null, 1) {

    companion object {
        //USER
        val TABLE_USER = "User"
        val USER_NAME = "Username"
        val USER_PASSWORD = "Password"
        val USER_ROL_NAME = "UserRolName"

        //ROL
        val TABLE_ROL = "Rol"
        val ROL_NAME = "RolName"
        val ROL_DESC = "RolDesc"
        val IS_ADMIN = "IsAdmin"

        //PERMISSION
        val TABLE_PERMISSION = "Permission"
        val PERMISSION_ROL_NAME = "PermissionRolName"
        val PERMISSION_SCREEN = "PermissionScreen"
        val PERMISSION_ACCESS = "PermissionAccess"
        val PERMISSION_INSERT = "PermissionInsert"
        val PERMISSION_UPDATE = "PermissionUpdate"
        val PERMISSION_DELETE = "PermissionDelete"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE " + TABLE_ROL + "(" +
                ROL_NAME + " TEXT PRIMARY KEY, " +
                ROL_DESC + " TEXT, " +
                IS_ADMIN + " INT NOT NULL" +
                ");")

        db.execSQL("CREATE TABLE " + TABLE_PERMISSION + "(" +
                PERMISSION_ROL_NAME + " TEXT NOT NULL, " +
                PERMISSION_SCREEN + " TEXT NOT NULL, " +
                PERMISSION_ACCESS + " INT NOT NULL, " +
                PERMISSION_INSERT + " INT NOT NULL, " +
                PERMISSION_UPDATE + " INT NOT NULL, " +
                PERMISSION_DELETE + " INT NOT NULL, " +
                "PRIMARY KEY ($PERMISSION_ROL_NAME, $PERMISSION_SCREEN), " +
                "FOREIGN KEY (" + PERMISSION_ROL_NAME + ") REFERENCES " + TABLE_ROL + " (" + ROL_NAME + ")" +
                ");")

        db.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                USER_NAME + " TEXT PRIMARY KEY, " +
                USER_PASSWORD + " TEXT NOT NULL, " +
                USER_ROL_NAME + " TEXT NOT NULL," +
                "FOREIGN KEY (" + USER_ROL_NAME + ") REFERENCES " + TABLE_ROL + " (" + ROL_NAME + ")" +
                ");")

        val contentValues = ContentValues()
        contentValues.put(ROL_NAME, Rol.ADMIN);
        contentValues.put(ROL_DESC, "Administrator description")
        contentValues.put(IS_ADMIN, 1)
        db.insert(TABLE_ROL, null, contentValues)

        contentValues.put(ROL_NAME, Rol.USER);
        contentValues.put(ROL_DESC, "User decription")
        contentValues.put(IS_ADMIN, 0)
        db.insert(TABLE_ROL, null, contentValues)

        contentValues.put(ROL_NAME, Rol.GUEST);
        contentValues.put(ROL_DESC, "Guest description")
        contentValues.put(IS_ADMIN, 0)
        db.insert(TABLE_ROL, null, contentValues)

        contentValues.clear()
        contentValues.put(PERMISSION_ROL_NAME, Rol.ADMIN)
        contentValues.put(PERMISSION_SCREEN, "Muestras")
        contentValues.put(PERMISSION_ACCESS, 1)
        contentValues.put(PERMISSION_INSERT, 1)
        contentValues.put(PERMISSION_UPDATE, 1)
        contentValues.put(PERMISSION_DELETE, 1)
        db.insert(TABLE_PERMISSION, null, contentValues)

        contentValues.put(PERMISSION_ROL_NAME, Rol.USER)
        contentValues.put(PERMISSION_SCREEN, "Muestras")
        contentValues.put(PERMISSION_ACCESS, 1)
        contentValues.put(PERMISSION_INSERT, 0)
        contentValues.put(PERMISSION_UPDATE, 1)
        contentValues.put(PERMISSION_DELETE, 0)
        db.insert(TABLE_PERMISSION, null, contentValues)

        contentValues.put(PERMISSION_ROL_NAME, Rol.GUEST)
        contentValues.put(PERMISSION_SCREEN, "Muestras")
        contentValues.put(PERMISSION_ACCESS, 1)
        contentValues.put(PERMISSION_INSERT, 0)
        contentValues.put(PERMISSION_UPDATE, 0)
        contentValues.put(PERMISSION_DELETE, 0)
        db.insert(TABLE_PERMISSION, null, contentValues)

        contentValues.clear()
        contentValues.put(USER_NAME, "admin")
        contentValues.put(USER_PASSWORD, "admin")
        contentValues.put(USER_ROL_NAME, Rol.ADMIN)
        db.insert(TABLE_USER, null, contentValues)

        contentValues.put(USER_NAME, "user")
        contentValues.put(USER_PASSWORD, "1234")
        contentValues.put(USER_ROL_NAME, Rol.USER)
        db.insert(TABLE_USER, null, contentValues)

        contentValues.put(USER_NAME, "guest")
        contentValues.put(USER_PASSWORD, "1234")
        contentValues.put(USER_ROL_NAME, Rol.GUEST)
        db.insert(TABLE_USER, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {     }

}