package com.example.cultivosbiologicos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.ArrayAdapter
import android.widget.CursorAdapter

class SamplesDB(val ctx : Context) : SQLiteOpenHelper(ctx, "Samples", null, 1) {

    companion object {
        //SAMPLE
        val TABLE_SAMPLE = "Samples"
        val SAMPLE_ID = "SampleID"
        val SAMPLE_PATIENT = "SamplePacient"
        val SAMPLE_CULTURE = "SampleCulture"
        val SAMPLE_SOLUTION = "SampleSolution"

        //SOLUTION
        val TABLE_SOLUTION = "Solutions"
        val SOLUTION_ID = "SolutionID"
        val SOLUTION = "Solution"
        val SOLUTION_USE = "SolutionUse"

        val SOLUTIONS_LIST = listOf(
            "Ácido-Alcohol",
            "Azul de metileno",
            "Azul de metileno de Loeffler",
            "Colorante para esporas",
            "Colorante para flagelos de Leifson",
            "Lugol",
            "Orceína A",
            "Orceína B",
            "Safranina",
            "Tinción de Papanicolaou"
        )
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE $TABLE_SOLUTION (" +
                "$SOLUTION_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$SOLUTION TEXT, " +
                "$SOLUTION_USE TEXT " +
                ");")

        db.execSQL("CREATE TABLE $TABLE_SAMPLE (" +
                "$SAMPLE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$SAMPLE_PATIENT TEXT, " +
                "$SAMPLE_CULTURE TEXT, " +
                "$SAMPLE_SOLUTION INTEGER NOT NULL, " +
                "FOREIGN KEY ($SAMPLE_SOLUTION) REFERENCES $TABLE_SOLUTION($SOLUTION_ID)" +
                ");")


        val contentValues = ContentValues()

        SOLUTIONS_LIST.forEach {

            contentValues.put(SOLUTION, it)
            db.insert(TABLE_SOLUTION, null, contentValues)
        }

        contentValues.clear()

        contentValues.put(SAMPLE_PATIENT, "11111111A")
        contentValues.put(SAMPLE_CULTURE, "Sangre")
        contentValues.put(SAMPLE_SOLUTION, 3)
        db.insert(TABLE_SAMPLE, null, contentValues)

        contentValues.put(SAMPLE_PATIENT, "22222222B")
        contentValues.put(SAMPLE_CULTURE, "Orina")
        contentValues.put(SAMPLE_SOLUTION, 5)
        db.insert(TABLE_SAMPLE, null, contentValues)

        contentValues.put(SAMPLE_PATIENT, "11111111A")
        contentValues.put(SAMPLE_CULTURE, "Citología")
        contentValues.put(SAMPLE_SOLUTION, 2)
        db.insert(TABLE_SAMPLE, null, contentValues)

        contentValues.put(SAMPLE_PATIENT, "22222222B")
        contentValues.put(SAMPLE_CULTURE, "Heces")
        contentValues.put(SAMPLE_SOLUTION, 1)
        db.insert(TABLE_SAMPLE, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    fun getAllSamples() : List<Sample>{

        val result = mutableListOf<Sample>()

        val query = "SELECT * FROM $TABLE_SAMPLE"

        val cursor = readableDatabase.rawQuery(query, arrayOf())

        cursor.moveToFirst()

        while(!cursor.isAfterLast) {

            val id = cursor.getInt(cursor.getColumnIndex(SAMPLE_ID))

            result.add(Sample(ctx, id))

            cursor.moveToNext()
        }

        cursor.close()

        return result
    }

    fun getAllSolutions() : List<Solution> {

        val result = mutableListOf<Solution>()

        val query = "SELECT * FROM $TABLE_SOLUTION"

        val cursor = readableDatabase.rawQuery(query, arrayOf())

        cursor.moveToFirst()

        while(!cursor.isAfterLast) {

            val id = cursor.getInt(cursor.getColumnIndex(SOLUTION_ID))

            result.add(Solution(ctx, id))

            cursor.moveToNext()
        }

        cursor.close()

        return result
    }
}