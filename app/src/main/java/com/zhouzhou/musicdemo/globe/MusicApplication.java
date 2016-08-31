package com.zhouzhou.musicdemo.globe;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

/**
 * Created by zhousaito on 2016/8/31.
 */
public class MusicApplication extends Application {
    private static Handler sHandler;

    @Override
    public void onCreate() {
        sHandler = new Handler();
        super.onCreate();
    }
    public static Handler getHandler(){
        return sHandler;
    }
}
