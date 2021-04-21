package com.example.bluetoothconnect

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothHeadset
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.content.IntentFilter
import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SixthActivity : AppCompatActivity() {

    val bluetoothAdapter:BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    val bluetoothProfileListener = object: BluetoothProfile.ServiceListener {
        override fun onServiceConnected(p0: Int, p1: BluetoothProfile?) {
            Log.d("TAG", "onServiceConnected: ")
        }

        override fun onServiceDisconnected(p0: Int) {
            Log.d("TAG", "onServiceDisconnected: ")
        }

    }

    val mediaSessionCallback: MediaSession.Callback = object: MediaSession.Callback() {
        override fun onMediaButtonEvent(mediaButtonIntent: Intent): Boolean {
            Log.d("TAG", "onMediaButtonEvent: ")
            Log.d("TAG", mediaButtonIntent.toString())
            return super.onMediaButtonEvent(mediaButtonIntent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        init()
    }

    private fun init() {

        bluetoothAdapter.getProfileProxy(this, bluetoothProfileListener, BluetoothProfile.HEADSET)
        val audioSession = MediaSession(this, "BLUETOOTH")
        audioSession.isActive = true
        audioSession.setCallback(mediaSessionCallback)
    }
}