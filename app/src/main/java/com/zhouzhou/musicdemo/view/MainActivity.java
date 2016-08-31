package com.zhouzhou.musicdemo.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.zhouzhou.musicdemo.R;
import com.zhouzhou.musicdemo.globe.MusicApplication;
import com.zhouzhou.musicdemo.receiver.MusicReceiver;
import com.zhouzhou.musicdemo.service.IService;
import com.zhouzhou.musicdemo.service.MusicService;
import com.zhouzhou.musicdemo.utils.ConstantValue;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private MyServiceConnection mMyServiceConnection;
    private IService mIService;
    private SeekBar mSeekBar;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ConstantValue.SEEK_BAR_START:  //播放开始,seek bar开始动
                    Log.e("自定义标签", "类名==MainActivity" + "方法名==handleMessage=====:" + msg.arg1);
                    mSeekBar.setMax(msg.arg1);
                    mHandler.sendEmptyMessageDelayed(ConstantValue.SEEK_BAR_UPDATE,200);
                    break;
                case ConstantValue.SEEK_BAR_UPDATE://播放开始,seek bar 更新进度
                    /**
                     * 每200毫秒 进度加200
                     */
                    mSeekBar.setProgress(mSeekBar.getProgress()+200);
                    mHandler.sendEmptyMessageDelayed(ConstantValue.SEEK_BAR_UPDATE,200);
                    break;
                case ConstantValue.SEEK_BAR_STOP:  //播放完成
                    mSeekBar.setProgress(0);
                    break;
            }
        }
    };
    private MusicReceiver mMusicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initService();

        initView();
        //动态注册广播
        IntentFilter intentFilter = new IntentFilter("www.zhousaito.com");
        mMusicReceiver = new MusicReceiver(mHandler);
        registerReceiver(mMusicReceiver,intentFilter);
    }

    private void initView() {
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    /**
     * 初始化,绑定服务
     */
    private void initService() {
        mMyServiceConnection = new MyServiceConnection();
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, mMyServiceConnection, BIND_AUTO_CREATE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //开始播放
            case R.id.start:
                mIService.startMp3();
                break;
            //停止播放
            case R.id.pause:
                mIService.pauseMp3();
                break;
        }
    }

    /**
     * 变化
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    /**
     * 手指按下
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 手指抬起来
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIService = (IService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        //解绑
        unbindService(mMyServiceConnection);
        unregisterReceiver(mMusicReceiver);
        super.onDestroy();
    }

}
