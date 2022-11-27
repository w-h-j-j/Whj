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

    public void setData(String value){
        liveData.setValue(String.valueOf(value));
    }
}
