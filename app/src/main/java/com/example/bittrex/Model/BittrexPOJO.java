package com.example.bittrex.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class BittrexPOJO implements Serializable {

    @SerializedName("result")
    @Expose
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public BittrexPOJO(List<Result> result) {
        this.result = result;
    }
}

