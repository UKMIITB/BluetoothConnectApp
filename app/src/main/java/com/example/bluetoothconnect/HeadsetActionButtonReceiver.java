package com.example.bluetoothconnect;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.view.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

public class HeadsetActionButtonReceiver extends BroadcastReceiver {

    public static Delegate delegate;

    private static AudioManager mAudioManager;
    private static ComponentName mRemoteControlResponder;

    private static int doublePressSpeed = 300; // double keypressed in ms
    private static Timer doublePressTimer;
    private static int counter;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive: of HeadsetActionButtonReceiver. Intent is :"+intent.toString());

        if (intent == null || delegate == null || !Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction()))
            return;

        KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
        if (keyEvent == null || keyEvent.getAction() != KeyEvent.ACTION_DOWN) return;

        counter++;
        if (doublePressTimer != null) {
            doublePressTimer.cancel();
        }
        doublePressTimer = new Timer();

        doublePressTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (counter == 1) {
                    delegate.onMediaButtonSingleClick();
                } else {
                    delegate.onMediaButtonDoubleClick();
                }
                counter = 0;
            }
        }, doublePressSpeed);
    }

    public interface Delegate {
        void onMediaButtonSingleClick();

        void onMediaButtonDoubleClick();
    }

    public static void register(final Context context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mRemoteControlResponder = new ComponentName(context, HeadsetActionButtonReceiver.class);
        mAudioManager.registerMediaButtonEventReceiver(mRemoteControlResponder);
    }

    public static void unregister(final Context context) {
        mAudioManager.unregisterMediaButtonEventReceiver(mRemoteControlResponder);
        if (doublePressTimer != null) {
            doublePressTimer.cancel();
            doublePressTimer = null;
        }
    }
}
