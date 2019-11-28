package com.example.cultivosbiologicos

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.manage_sample.*
import kotlinx.android.synthetic.main.manage_sample.culture
import kotlinx.android.synthetic.main.manage_sample.patient

class ManageSampleActivity : AppCompatActivity() {

    companion object {
        val INSERT = "Insert"
        val UPDATE = "Update"
        val DELETE = "Delete"
        val SAMPLE = "Sample"
    }

    private lateinit var adapter : SpinAdapter
    private var sample : Sample? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_sample)

        val db = SamplesDB(this)

        val solutions = db.getAllSolutions()

        adapter = SpinAdapter(this, android.R.layout.simple_spinner_item, solutions)

        spinner.adapter = adapter

        insert.setOnClickListener { insert() }

        update.setOnClickListener { update() }

        delete.setOnClickListener { delete() }

        showSample()

        managePermissions()
    }

    private fun showSample() {

        val id = intent.extras?.getInt(SAMPLE, -1)!!

        if (id != -1) {

            sample = Sample(this, id)

            patient.setText(sample?.patient)

            culture.setText(sample?.culture)

            spinner.setSelection(adapter.getPosition(sample?.solution))
        }

    }

    private fun managePermissions() {

        if ( ! intent.extras?.getBoolean(INSERT, false)!!)  insert.visibility = View.INVISIBLE

        if ( ! intent.extras?.getBoolean(DELETE, false)!!)  delete.visibility = View.INVISIBLE

        if ( ! intent.extras?.getBoolean(UPDATE, false)!!)  update.visibility = View.INVISIBLE
    }

    private fun insert() {

        val paciente = patient.text.toString()
        val cultivo = culture.text.toString()
        val solucion = spinner.selectedItem as Solution

        Sample(this, paciente, cultivo, solucion)

        finish()
    }

    private fun update() {

        val paciente = patient.text.toString()
        val cultivo = culture.text.toString()
        val solucion = spinner.selectedItem as Solution

        sample?.update(paciente, cultivo, solucion)

        finish()
    }

    private fun delete() {

        sample?.delete()

        finish()
    }
}