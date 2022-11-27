package com.mingrisoft.whj.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class OneService extends Service {

    private String TAG="测试Service";
    private MediaPlayer mediaPlayer;
    private ServiceReceiver receiver;
    /** 1:没有声音 2：正在播放 3：暂停 */
    public int status;
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        status=1;
        receiver=new ServiceReceiver();
        IntentFilter filter=new IntentFilter("cn.com.sgmsc.MusicPlayer.control");
        registerReceiver(receiver,filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return null;
    }

    public class MyBinder extends Binder{
        public void print(){
            System.out.println("哈哈");
        }
    }
}
