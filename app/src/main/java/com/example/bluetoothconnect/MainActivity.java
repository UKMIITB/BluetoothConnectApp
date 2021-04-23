package com.example.bluetoothconnect;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaBrowserCompat mMediaBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mMediaBrowser = new MediaBrowserCompat(this, new ComponentName(this, MediaPlaybackService.class),
                new MediaBrowserCompat.ConnectionCallback(), null);
        mMediaBrowser.connect();
    }

    @Override
    protected void onDestroy() {
        mMediaBrowser.disconnect();
        super.onDestroy();
    }
}