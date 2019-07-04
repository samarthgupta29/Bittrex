package com.example.bittrex.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bittrex.Model.Result;
import com.example.bittrex.R;

import java.util.List;

public class BittrexAdapter extends RecyclerView.Adapter<BittrexAdapter.BittrexViewHolder> {

    private List<Result> resultList;
    public BittrexAdapter(List<Result> resultList) {
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public BittrexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bittrex_item, viewGroup, false);
        return new BittrexViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BittrexViewHolder bittrexViewHolder, int i) {
        bittrexViewHolder.currencyTv.setText(resultList.get(i).getCurrency());
        bittrexViewHolder.currencyLongTv.setText(resultList.get(i).getCurrencyLong());
        bittrexViewHolder.txFeeTv.setText(String.valueOf(resultList.get(i).getTxFee()));
    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }


    public class BittrexViewHolder extends RecyclerView.ViewHolder {

        private TextView currencyTv, currencyLongTv, txFeeTv;

        public BittrexViewHolder(View itemView) {
            super(itemView);
            currencyTv = itemView.findViewById(R.id.currencyTv);
            currencyLongTv = itemView.findViewById(R.id.currencyLongTv);
            txFeeTv = itemView.findViewById(R.id.txFeeTv);
        }
    }
}
