package com.example.bittrex.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.bittrex.DAO.BittrexDAO;
import com.example.bittrex.Model.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class BittrexDatabase extends RoomDatabase {
    public abstract BittrexDAO bittrexDAO();

    private static BittrexDatabase bittrexDatabase;

    public static BittrexDatabase getInstance(Context context) {
        if (bittrexDatabase == null) {
            bittrexDatabase = Room.databaseBuilder(context.getApplicationContext(), BittrexDatabase.class, "coin-db").allowMainThreadQueries().build();
        }
        return bittrexDatabase;
    }
}
