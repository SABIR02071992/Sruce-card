package com.mpfaith.adminmp5.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {



    public static MutableLiveData<String> FragmentIntentCheck = new MutableLiveData();
    public static MutableLiveData<String> text = new MutableLiveData();

    public static MutableLiveData<String> getFragmentIntentCheck() {

        return FragmentIntentCheck;
    }  public static MutableLiveData<String> getHomeText() {

        return text;
    }
}
