package com.mingrisoft.whj.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BatteryListener {

    private String TAG="我的广播";
    private Context context;
    private BatteryBroadcastReceiver receiver;
    private IBatteryStateListener iBatteryStateListener;


    public BatteryListener(Context context) {
        this.context = context;
        receiver = new BatteryBroadcastReceiver();
    }

    //注册广播
    public void register(IBatteryStateListener iBatteryStateListener) {
        this.iBatteryStateListener = iBatteryStateListener;
        if (receiver != null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_BATTERY_CHANGED);
            filter.addAction(Intent.ACTION_BATTERY_LOW);
            filter.addAction(Intent.ACTION_BATTERY_OKAY);
            filter.addAction(Intent.ACTION_POWER_CONNECTED);
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            filter.addAction(Intent.ACTION_HEADSET_PLUG);
            context.registerReceiver(receiver, filter);
        }
    }

    //取消注册
    public void unRegister() {
        if (receiver != null) {
            context.unregisterReceiver(receiver);
        }
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                switch (action) {
                    case Intent.ACTION_BATTERY_CHANGED://电量发生改变
                        if (iBatteryStateListener != null) {
                            Log.e(TAG, "电量发生改变");
                            iBatteryStateListener.onStateChanged();
                        }
                        break;
                    case Intent.ACTION_BATTERY_LOW://电量低
                        if (iBatteryStateListener != null) {
                            Log.e(TAG, "电量低");
                            iBatteryStateListener.onStateLow();
                        }
                        break;
                    case Intent.ACTION_BATTERY_OKAY://电量充满
                        if (iBatteryStateListener != null) {
                            Log.e(TAG, "电量充满");
                            iBatteryStateListener.onStateOkay();
                        }
                        break;
                    case Intent.ACTION_POWER_CONNECTED://接通电源
                        if (iBatteryStateListener != null) {
                            Log.e(TAG, "接通电源");
                            iBatteryStateListener.onStatePowerConnected();
                        }
                        break;
                    case Intent.ACTION_POWER_DISCONNECTED://拔出电源
                        if (iBatteryStateListener != null) {
                            Log.e(TAG, "拔出电源");
                            iBatteryStateListener.onStatePowerDisconnected();
                        }
                        break;
                    case Intent.ACTION_HEADSET_PLUG://插入耳机
                        if (iBatteryStateListener != null) {
                            if (intent.getIntExtra("state", 0) == 1){
                                Log.e(TAG, "插入耳机");
                                iBatteryStateListener.onStateHeadsetPlug();
                            }else {
                                Log.e(TAG, "拔出耳机");
                                iBatteryStateListener.onStateHeadsetUnPlug();
                            }
                        }
                        break;
                }
            }

        }
    }
}
