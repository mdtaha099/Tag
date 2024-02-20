package com.example.tag

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

//
//        val notification = NotificationCompat.Builder(this, "location11")
//            .setContentTitle("Tracking location...")
//            .setContentText("Location: null")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setOngoing(true)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        locationClient
//            .getLocationUpdates(10000L)
//            .catch { e -> e.printStackTrace() }
//            .onEach { location ->
//                val lat = location.latitude.toString().takeLast(3)
//                val long = location.longitude.toString().takeLast(3)
//                val updatedNotification = notification.setContentText(
//                    "Location: ($lat, $long)"
//                )
//                notificationManager.notify(1, updatedNotification.build())
//            }
//            .launchIn(serviceScope)
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
//            startForeground(1, notification.build())
//        } else {
//            startForeground(1, notification.build())
//
//        }
//        startForeground(1, notification.build())

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val chan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                "NOTIFICATION_CHANNEL_ID",
                "channelName",
                NotificationManager.IMPORTANCE_NONE
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        chan.description = "for muting"

        manager.createNotificationChannel(chan)

        manager.createNotificationChannel(
            NotificationChannel(
                "name",
                "Muter",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

//        Log.d("TAG", "success" )

        val notificationBuilder = NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL_ID")
        val notification = notificationBuilder.setOngoing(true)
            .setContentTitle("appname")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        startForeground(4, notification)
    }


        private fun stop() {
        stopForeground(true)
        stopSelf()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        serviceScope.cancel()
//    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}