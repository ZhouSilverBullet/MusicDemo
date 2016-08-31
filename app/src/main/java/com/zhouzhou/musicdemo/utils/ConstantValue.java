package com.zhouzhou.musicdemo.utils;

/**
 * Created by zhousaito on 2016/8/31.
 */
public interface ConstantValue {
    /////////////////////////////////////
    /**
     * 音乐开始
     */
    int MUSIC_STATE_START = 0;
    /**
     * 音乐暂停
     */
    int MUSIC_STATE_PAUSE = 1;

    /////////////////播放的时候intent的键///////////////////
    String MUSIC_KEY ="music_key";

    ////////////////////////////////////////////////////
    /**
     * seek bar 开始动
     */
    int SEEK_BAR_START = 2;
    /**
     * 最大值
     */
    String MAX = "max";
    /**
     * seek bar 更新
     */
    int SEEK_BAR_UPDATE =100;
    /** 即 播放完成
     * seek bar 停止
     *
     */
    int SEEK_BAR_STOP = 101;
}
