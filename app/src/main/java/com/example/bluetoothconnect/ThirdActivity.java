package com.example.bluetoothconnect;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media.session.MediaButtonReceiver;

import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private MediaBrowserCompat mMediaBrowser;
    private final String TAG = "TAG";
    private MediaSessionCompat mMediaSession;
    private ComponentName mediaButtonReceiver;

    MediaControllerCompat.Callback callback = new MediaControllerCompat.Callback() {
        @Override
        public void onSessionReady() {
            Log.d(TAG, "onSessionReady: ");
            super.onSessionReady();
        }

        @Override
        public void onSessionDestroyed() {
            Log.d(TAG, "onSessionDestroyed: ");
            super.onSessionDestroyed();
        }

        @Override
        public void onSessionEvent(String event, Bundle extras) {
            Log.d(TAG, "onSessionEvent: "+event.toString());
            Log.d(TAG, "onSessionEvent: "+extras.toString());
            super.onSessionEvent(event, extras);
        }

        @Override
        public void onPlaybackStateChanged(PlaybackStateCompat state) {
            Log.d(TAG, "onPlaybackStateChanged: "+state.toString());
            super.onPlaybackStateChanged(state);
        }

        @Override
        public void onMetadataChanged(MediaMetadataCompat metadata) {
            Log.d(TAG, "onMetadataChanged: "+metadata.toString());
            super.onMetadataChanged(metadata);
        }

        @Override
        public void onQueueChanged(List<MediaSessionCompat.QueueItem> queue) {
            Log.d(TAG, "onQueueChanged: "+queue.toString());
            super.onQueueChanged(queue);
        }

        @Override
        public void onQueueTitleChanged(CharSequence title) {
            Log.d(TAG, "onQueueTitleChanged: ");
            super.onQueueTitleChanged(title);
        }

        @Override
        public void onExtrasChanged(Bundle extras) {
            Log.d(TAG, "onExtrasChanged: "+extras.toString());
            super.onExtrasChanged(extras);
        }

        @Override
        public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo info) {
            Log.d(TAG, "onAudioInfoChanged: "+info.toString());
            super.onAudioInfoChanged(info);
        }

        @Override
        public void onCaptioningEnabledChanged(boolean enabled) {
            super.onCaptioningEnabledChanged(enabled);
        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {
            super.onRepeatModeChanged(repeatMode);
        }

        @Override
        public void onShuffleModeChanged(int shuffleMode) {
            super.onShuffleModeChanged(shuffleMode);
        }
        
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied: ");
            super.binderDied();
        }

//        @Override
//        public IMediaControllerCallback getIControllerCallback() {
//            return super.getIControllerCallback();
//        }
    };

    MediaSessionCompat.Callback mediaSessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public void onCommand(String command, Bundle extras, ResultReceiver cb) {
            Log.d(TAG, "onCommand: ");
            super.onCommand(command, extras, cb);
        }

        @Override
        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
            Log.d(TAG, "onMediaButtonEvent: "+mediaButtonEvent.toString());
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        init();
    }

    private void init() {
        mMediaBrowser = new MediaBrowserCompat(this, new ComponentName(this, MediaPlaybackService.class),
                new MediaBrowserCompat.ConnectionCallback() {
                    @Override
                    public void onConnected() {
                        Log.d(TAG, "onConnected: ");
                        try {
//                            MediaSessionCompat.Token token =
//                                    mMediaBrowser.getSessionToken();
//                            MediaControllerCompat controller =
//                                    new MediaControllerCompat(ThirdActivity.this, token);
//                            MediaControllerCompat.setMediaController(
//                                    ThirdActivity.this, controller);
//                            controller.registerCallback(mediaSessionCallback);
//                            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, "TAG")

                            mediaButtonReceiver = new ComponentName(getApplicationContext(), MediaButtonReceiver.class);
                            mMediaSession = new MediaSessionCompat(getApplicationContext(), "TAG", mediaButtonReceiver, null);
                            mMediaSession.setCallback(mediaSessionCallback);

                        } catch (Exception e) {
                            Log.e("TAG",
                                    "Error creating controller", e);
                        }
                    }

                    @Override
                    public void onConnectionSuspended() {
                        Log.e("TAG", "onConnectionSuspended: " );
                    }

                    @Override
                    public void onConnectionFailed() {
                        Log.e("TAG", "onConnectionFailed: ");
                    }
                }, null);

        mMediaBrowser.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaBrowser.disconnect();
    }
}