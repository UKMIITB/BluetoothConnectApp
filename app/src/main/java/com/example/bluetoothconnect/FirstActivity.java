package com.example.bluetoothconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class FirstActivity extends AppCompatActivity implements HeadsetActionButtonReceiver.Delegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    public void onMediaButtonSingleClick() {
        Log.d("TAG", "onMediaButtonSingleClick: ");
    }

    @Override
    public void onMediaButtonDoubleClick() {
        Log.d("TAG", "onMediaButtonDoubleClick: ");
    }

    @Override
    protected void onResume() {

        HeadsetActionButtonReceiver.delegate = this;
        HeadsetActionButtonReceiver.register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        HeadsetActionButtonReceiver.unregister(this);
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("ABC", "onKeyDown: ");
        Log.d("ABC", "Event is: "+event.toString());
        Log.d("ABC", "keycode: "+keyCode);
        return super.onKeyDown(keyCode, event);
    }
}