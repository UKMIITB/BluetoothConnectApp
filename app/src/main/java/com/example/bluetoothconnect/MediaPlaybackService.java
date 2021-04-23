package com.example.bluetoothconnect;

import android.content.ComponentName;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.session.MediaButtonReceiver;

import java.util.List;

public class MediaPlaybackService extends MediaBrowserServiceCompat {

    private final String TAG = "TAG";
    private Ringtone ringTone;
    private boolean isPlaying = true;

    @Override
    public void onCreate() {
        super.onCreate();
        ComponentName mediaButtonReceiver = new ComponentName(getApplicationContext(), MediaButtonReceiver.class);
        MediaSessionCompat mMediaSession = new MediaSessionCompat(getApplicationContext(), "TAG", mediaButtonReceiver, null);
        mMediaSession.setCallback(mediaSessionCallback);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringTone = RingtoneManager.getRingtone(getApplicationContext(), notification);
        playRingtone();
    }

    @Nullable
    @Override
    public BrowserRoot onGetRoot(@NonNull String clientPackageName, int clientUid, @Nullable Bundle rootHints) {
        Log.d("TAG", "onGetRoot: ");
        return new BrowserRoot("TAG", null);
    }

    @Override
    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        Log.d("TAG", "onLoadChildren: ");
        result.sendResult(null);
    }

    MediaSessionCompat.Callback mediaSessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public void onCommand(String command, Bundle extras, ResultReceiver cb) {
            Log.d(TAG, "onCommand: ");
            super.onCommand(command, extras, cb);
        }

        @Override
        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {

            KeyEvent keyEvent = mediaButtonEvent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);

            if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                Log.d(TAG, "onMediaButtonEvent: ");
                if(isPlaying)
                    pauseRingtone();
                else
                    playRingtone();
            }
            return super.onMediaButtonEvent(mediaButtonEvent);
        }

        @Override
        public void onPrepare() {
            Log.d(TAG, "onPrepare: ");
            super.onPrepare();
        }

        @Override
        public void onPlay() {
            Log.d(TAG, "onPlay: ");
            super.onPlay();
        }

        @Override
        public void onPause() {
            Log.d(TAG, "onPause: ");
            super.onPause();
        }

        @Override
        public void onSkipToNext() {
            Log.d(TAG, "onSkipToNext: ");
            super.onSkipToNext();
        }

        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
        }

        @Override
        public void onFastForward() {
            super.onFastForward();
        }

        @Override
        public void onRewind() {
            super.onRewind();
        }

        @Override
        public void onStop() {
            Log.d(TAG, "onStop: ");
            super.onStop();
        }
    };

    private void playRingtone() {
        ringTone.play();
        ringTone.setLooping(true);
        isPlaying = true;
    }

    private void pauseRingtone() {
        ringTone.stop();
        isPlaying = false;
    }
}