package com.example.bittrex.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.bittrex.Model.BittrexPOJO;
import com.example.bittrex.Repository.BittrexRepository;

public class BittrexViewModel extends ViewModel {

    private MutableLiveData<BittrexPOJO> bittrexPOJOMutableLiveData;
    private BittrexRepository bittrexRepository;

    public void init(){
        if(bittrexPOJOMutableLiveData != null){
            return;
        }
        bittrexRepository = BittrexRepository.getInstance();
        bittrexPOJOMutableLiveData = bittrexRepository.getData();
    }

    public LiveData<BittrexPOJO> getBittrexRepository(){
        return bittrexPOJOMutableLiveData;
    }
}
