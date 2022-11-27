package com.mingrisoft.whj.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int action = intent.getIntExtra("ACTION", -1);
        switch (action){
            case 1:

                break;
            case 2:

                break;
            default:
                break;
        }
    }
}
