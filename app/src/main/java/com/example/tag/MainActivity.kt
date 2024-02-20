package com.example.tag

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val NAME_KEY = "name_key"
        const val MOBILE_KEY = "mobile_key"
    }

    private lateinit var sharedpreferences: SharedPreferences
    private var name: String? = null
    private var mobile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val i = Intent(this@MainActivity, Mappy::class.java)
        startActivity(i)

        val nameEdt = findViewById<EditText>(R.id.name)
        val mobileEdt = findViewById<EditText>(R.id.mobile_no)
        val loginBtn = findViewById<Button>(R.id.login_btn)

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

        name = sharedpreferences.getString(NAME_KEY, null)
        mobile = sharedpreferences.getString(MOBILE_KEY, null)

        loginBtn.setOnClickListener {
            // to check if the user fields are empty or not.
            if (TextUtils.isEmpty(nameEdt.text.toString()) || TextUtils.isEmpty(mobileEdt.text.toString())) {
                // this method will call when email and password fields are empty.
                Toast.makeText(
                    this@MainActivity,
                    "Please Enter Email and Password",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if(mobileEdt.text.toString().length != 10){
                Toast.makeText(
                    this@MainActivity,
                    "Please enter 10 digit number",
                    Toast.LENGTH_SHORT
                ).show()
            }

             else {
                val editor = sharedpreferences.edit()

                // below two lines will put values for
                // email and password in shared preferences.
                editor.putString(NAME_KEY, nameEdt.text.toString())
                editor.putString(MOBILE_KEY, mobileEdt.text.toString())

                // to save our data with key and value.
                editor.apply()

                // starting new activity.
                val i = Intent(this@MainActivity, Mappy::class.java)
                startActivity(i)
                finish()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (name != null && mobile != null) {
            val i = Intent(this@MainActivity, Mappy::class.java)
            startActivity(i)
        }
    }

}
