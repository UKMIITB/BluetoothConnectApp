package com.example.bluetoothconnect;

import android.content.ComponentName;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
        playRingtone();
    }

    private void init() {
        mMediaBrowser = new MediaBrowserCompat(this, new ComponentName(this, MediaPlaybackService.class),
                new MediaBrowserCompat.ConnectionCallback(), null);
        mMediaBrowser.connect();
    }

    private void playRingtone() {

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringTone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        ringTone.play();
        ringTone.setLooping(true);
    }

    @Override
    protected void onDestroy() {
        mMediaBrowser.disconnect();
        super.onDestroy();
    }
}