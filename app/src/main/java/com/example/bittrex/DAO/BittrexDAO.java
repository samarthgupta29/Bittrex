package com.example.bittrex.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.bittrex.Model.Result;

import java.util.List;

@Dao
public interface BittrexDAO {

    @Insert
    void insertData(Result result);

    @Query("SELECT * FROM Result")
    LiveData<List<Result>> fetchData();

    @Query("SELECT * FROM Result")
    List<Result> fetchListData();
}
