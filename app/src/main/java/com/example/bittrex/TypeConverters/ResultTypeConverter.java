package com.example.bittrex.TypeConverters;

import android.arch.persistence.room.TypeConverter;

import com.example.bittrex.Model.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ResultTypeConverter {
    @TypeConverter
    public String fromString(List<Result> resultList){
        if(resultList == null) return null;
        Gson gson = new Gson();
        Type type = new TypeToken<List<Result>>(){}.getType();
        return gson.toJson(resultList,type);
    }

    @TypeConverter
    public List<Result> toString(String resultString){
        if(resultString == null) return null;
        Gson gson=new Gson();
        Type type = new TypeToken<List<Result>>(){}.getType();
        return gson.fromJson(resultString,type);
    }
}
