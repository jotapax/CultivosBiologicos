package com.example.cultivosbiologicos

import android.content.Context

class Solution(ctx : Context, val id : Int ) {

    val solution : String
    val use : String?

    init {

        val db = SamplesDB(ctx).readableDatabase

        val query = "SELECT * FROM ${SamplesDB.TABLE_SOLUTION} WHERE ${SamplesDB.SOLUTION_ID} = ?"

        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        cursor.moveToFirst()

        this.solution = cursor.getString(cursor.getColumnIndex(SamplesDB.SOLUTION))
        this.use = cursor.getString(cursor.getColumnIndex(SamplesDB.SOLUTION_USE))

        cursor.close()
    }
}