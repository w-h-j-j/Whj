package com.mingrisoft.whj.broadcast;

public interface IBatteryStateListener {
    public void onStateChanged();
    public void onStateLow();
    public void onStateOkay();
    public void onStatePowerConnected();
    public void onStatePowerDisconnected();
    public void onStateHeadsetPlug();
    public void onStateHeadsetUnPlug();
}
