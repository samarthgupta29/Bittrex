package com.example.bittrex.View;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bittrex.Adapter.BittrexAdapter;
import com.example.bittrex.Database.BittrexDatabase;
import com.example.bittrex.Model.Result;
import com.example.bittrex.R;
import com.example.bittrex.ViewModel.BittrexViewModel;

public class MainActivity extends AppCompatActivity {

    private BittrexViewModel bittrexViewModel;
    private BittrexDatabase bittrexDatabase;
    private RecyclerView recyclerView;
    private BittrexAdapter bittrexAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.bittrexRv);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bittrexDatabase = BittrexDatabase.getInstance(this);
        bittrexViewModel = ViewModelProviders.of(this).get(BittrexViewModel.class);
        bittrexViewModel.init();
        bittrexViewModel.getBittrexRepository().observe(this, bittrexResponse -> {
            if (bittrexResponse != null) {
                for (Result result : bittrexResponse.getResult()) {
                    bittrexDatabase.bittrexDAO().insertData(new Result(result.getCurrency(), result.getCurrencyLong(), result.getTxFee()));
                }
            }
        });
        bittrexDatabase.bittrexDAO().fetchData().observe(this, resultList -> {
            if (resultList != null) {
                bittrexAdapter = new BittrexAdapter(resultList);
                recyclerView.setAdapter(bittrexAdapter);
            }
            if (resultList.size() == 0) {
                Toast.makeText(getApplicationContext(), "Please check for your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
