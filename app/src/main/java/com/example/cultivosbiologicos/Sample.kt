package com.example.cultivosbiologicos

import android.content.ContentValues
import android.content.Context

class Sample {

    val ctx : Context
    val id : Int
    var patient : String
    var culture : String
    var solution : Solution

    constructor(ctx : Context, id : Int) {

        this.ctx = ctx

        val db = SamplesDB(ctx).readableDatabase

        val query = "SELECT * FROM ${SamplesDB.TABLE_SAMPLE} WHERE ${SamplesDB.SAMPLE_ID} = ?"

        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        cursor.moveToFirst()

        this.id = id
        this.patient = cursor.getString(cursor.getColumnIndex(SamplesDB.SAMPLE_PATIENT))
        this.culture = cursor.getString(cursor.getColumnIndex(SamplesDB.SAMPLE_CULTURE))
        this.solution = Solution(ctx, cursor.getInt(cursor.getColumnIndex(SamplesDB.SAMPLE_SOLUTION)))

        cursor.close()
    }

    constructor(ctx: Context, paciente : String, cultivo : String, solution: Solution) {

        this.ctx = ctx

        val db = SamplesDB(ctx).writableDatabase

        val contentValues = ContentValues()
        contentValues.put(SamplesDB.SAMPLE_PATIENT, paciente)
        contentValues.put(SamplesDB.SAMPLE_CULTURE, cultivo)
        contentValues.put(SamplesDB.SAMPLE_SOLUTION, solution.id)

        this.id = db.insert(SamplesDB.TABLE_SAMPLE, null, contentValues).toInt()

        this.patient = paciente
        this.culture = cultivo
        this.solution = solution
    }

    fun delete() {

        val db = SamplesDB(ctx).writableDatabase

        db.delete(SamplesDB.TABLE_SAMPLE, "${SamplesDB.SAMPLE_ID} = ?", arrayOf(this.id.toString()))
    }

    fun update(paciente : String, cultivo : String, solution: Solution) {

        val db = SamplesDB(ctx).writableDatabase

        val contentValues = ContentValues()
        if (this.patient != paciente) contentValues.put(SamplesDB.SAMPLE_PATIENT, paciente)
        if (this.culture != cultivo) contentValues.put(SamplesDB.SAMPLE_CULTURE, cultivo)
        if (this.solution.id != solution.id) contentValues.put(SamplesDB.SAMPLE_SOLUTION, solution.id)

        db.update(SamplesDB.TABLE_SAMPLE, contentValues, "${SamplesDB.SAMPLE_ID} = ?", arrayOf(this.id.toString()))

    }
}