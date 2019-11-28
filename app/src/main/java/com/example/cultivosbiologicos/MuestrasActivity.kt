package com.example.cultivosbiologicos

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_muestras.*
import org.jetbrains.anko.toast

class MuestrasActivity : AppCompatActivity() {

    companion object{
        val USER_LOGGED = "user"
    }

    lateinit var user : User
    lateinit var permission : Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muestras)

        user = User(this, intent.extras?.getString(USER_LOGGED)!!)

        permission = Permission(this, Permission.SCREEN, user.rol)

        toast("Welcome back, ${user.username}")

        val bd = SamplesDB(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SampleAdapter(this, bd.getAllSamples())
    }

    override fun onResume() {
        super.onResume()

        val bd = SamplesDB(this)
        recyclerView.adapter = SampleAdapter(this, bd.getAllSamples())
    }

    fun manageSample(s : Sample) {

        if (permission.update || permission.delete) {
            val intent = Intent(this, ManageSampleActivity::class.java)
            intent.putExtra(ManageSampleActivity.DELETE, permission.delete)
            intent.putExtra(ManageSampleActivity.UPDATE, permission.update)
            intent.putExtra(ManageSampleActivity.SAMPLE, s.id)
            startActivity(intent)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (!permission.insert) {
            menu?.findItem(R.id.add)?.isVisible = false
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                val intent = Intent(this, ManageSampleActivity::class.java)
                intent.putExtra(ManageSampleActivity.INSERT, true)
                startActivity(intent)
                true
            }
            R.id.exit -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}