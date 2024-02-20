package com.example.tag

//import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat


//import org.greenrobot.eventbus.EventBus
//import org.greenrobot.eventbus.Subscribe
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationServices
//import com.google.firebase.database.FirebaseDatabase


class Mappy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )
//    setContent {
//        BackgroundLocationTrackingTheme {
//            Column(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                Button(onClick = {
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
//                }) {
//                    Text(text = "Start")
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = {
//                    Intent(applicationContext, LocationService::class.java).apply {
//                        action = LocationService.ACTION_STOP
//                        startService(this)
//                    }
//                }) {
//                    Text(text = "Stop")
//                }
//            }
//        }
//    }


    }
    }