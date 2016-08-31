package com.zhouzhou.musicdemo.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import com.zhouzhou.musicdemo.utils.ConstantValue;

import java.io.IOException;

public class MusicService extends Service {


    private MediaPlayer mMediaPlayer;
    private MyBinder mMyBinder;
    private Intent mIntent = new Intent("www.zhousaito.com");


    public MusicService() {
    }

    @Override
    public void onCreate() {
        mMediaPlayer = new MediaPlayer();
        mMyBinder = new MyBinder();

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }


    /**
     * 播放MP3
     */
    private void playStartMp3() {
        try {
            /**
             * reset() -- >在setDataSource()之前使用
             *
             *  java.lang.IllegalStateException
             */
            mMediaPlayer.reset();

            mMediaPlayer.setDataSource(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)) + "/lyzf.mp3");
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mIntent.putExtra(ConstantValue.MUSIC_KEY, ConstantValue.MUSIC_STATE_START);
            mIntent.putExtra(ConstantValue.MAX, mMediaPlayer.getDuration());
            sendBroadcast(mIntent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停
     */
    private void playPauseMp3() {
        mIntent.putExtra(ConstantValue.MUSIC_KEY, ConstantValue.MUSIC_STATE_PAUSE);
        mMediaPlayer.pause();
    }

    public class MyBinder extends Binder implements IService {
        @Override
        public void startMp3() {
            if (!mMediaPlayer.isPlaying()) {
                playStartMp3();
            }
        }

        @Override
        public void pauseMp3() {
            playPauseMp3();
        }


    }
}
