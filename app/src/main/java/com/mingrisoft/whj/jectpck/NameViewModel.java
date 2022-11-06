package com.mingrisoft.whj.jectpck;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {
    private MutableLiveData<String> liveData;

    public MutableLiveData<String> getLiveData(){
        if (liveData==null){
            liveData=new MutableLiveData<>();
        }
        return liveData;
    }

    public void setData(){
        int a=0;
        while (a<10){
            try {
                liveData.setValue(String.valueOf(a));
                a++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
