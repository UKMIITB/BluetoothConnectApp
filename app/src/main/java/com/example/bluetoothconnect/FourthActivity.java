package com.example.bluetoothconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class FourthActivity extends AppCompatActivity {

    FourthReceiver fourthReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        init();
    }

    public void init() {
        fourthReceiver = new FourthReceiver();
        registerReceiver(fourthReceiver, new IntentFilter(Intent.ACTION_MEDIA_BUTTON));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("TAG", "onKeyDown: ");
        Log.d("TAG", event.toString());
        return false;
    }
}