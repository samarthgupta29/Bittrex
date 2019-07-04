package com.example.bittrex.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Result implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("Currency")
    @Expose
    @ColumnInfo(name = "Currency")
    private String currency;

    @SerializedName("CurrencyLong")
    @Expose
    @ColumnInfo(name = "CurrencyLong")
    private String currencyLong;

    @SerializedName("TxFee")
    @Expose
    @ColumnInfo(name = "TxFee")
    private Double txFee;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyLong() {
        return currencyLong;
    }

    public void setCurrencyLong(String currencyLong) {
        this.currencyLong = currencyLong;
    }

    public Double getTxFee() {
        return txFee;
    }

    public void setTxFee(Double txFee) {
        this.txFee = txFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result(String currency, String currencyLong, Double txFee) {
        this.currency = currency;
        this.currencyLong = currencyLong;
        this.txFee = txFee;
    }
}
