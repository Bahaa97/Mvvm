package com.bahaa.mvvm.ui.fragment.offline;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bahaa.mvvm.MyApplication;
import com.bahaa.mvvm.R;
import com.bahaa.mvvm.data.network.ConnectivityStatus;
import com.bahaa.mvvm.util.AppTools;

public class OfflineViewModel extends ViewModel {

    public MutableLiveData<Integer> mutableLiveData;
    public OfflineViewModel() {
        mutableLiveData = new MutableLiveData<>();
    }


    public void onTryAgain(){
        mutableLiveData.setValue(AppTools.TRY_AGAIN);
    }
}