package com.zhouzhou.musicdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zhouzhou.musicdemo.utils.ConstantValue;

public class MusicReceiver extends BroadcastReceiver {
    private Handler mHandler;

    public MusicReceiver(Handler handler) {
        mHandler = handler;
    }

    public MusicReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int value = intent.getIntExtra(ConstantValue.MUSIC_KEY, -1);
        switch (value) {
            case ConstantValue.MUSIC_STATE_START:

                Message message = mHandler.obtainMessage();
                message.what=ConstantValue.SEEK_BAR_START;
                message.arg1 = intent.getIntExtra(ConstantValue.MAX,-1);
                message.sendToTarget();

                break;
             case ConstantValue.MUSIC_STATE_PAUSE:
                break;

        }
    }
}
