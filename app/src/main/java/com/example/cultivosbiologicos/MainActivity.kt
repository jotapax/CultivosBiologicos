package com.example.cultivosbiologicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {

            val username = user.text.toString()

            try {

                val u = User(this, username)

                val password = password.text.toString()

                if (u.password == password) {

                    launchScreen(u)

                } else {

                    toast("Incorrect password")
                }

            } catch (e : Exception) {

                toast("Username not found")
            }
        }
    }

    private fun launchScreen(user : User) {

        val permission = Permission(this, "Muestras", user.rol)

        if (!permission.access) {

            toast("Access not allowed")

        } else {

            val intent = Intent(this, MuestrasActivity::class.java)
            intent.putExtra(MuestrasActivity.USER_LOGGED, user.username)
            startActivity(intent)
        }
    }
}
