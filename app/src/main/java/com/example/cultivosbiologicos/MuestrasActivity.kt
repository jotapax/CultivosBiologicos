package com.example.cultivosbiologicos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.toast

class MuestrasActivity : AppCompatActivity() {

    companion object{
        val USER_LOGGED = "user"
    }

    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muestras)

        user = User(this, intent.extras?.getString(USER_LOGGED)!!)

        toast(user.username)
    }
}