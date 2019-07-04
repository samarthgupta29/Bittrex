package com.example.bittrex.Repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.bittrex.APIHelper.BittrexAPI;
import com.example.bittrex.Model.BittrexPOJO;
import com.example.bittrex.RetrofitClient.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BittrexRepository {

    private static BittrexRepository bittrexRepository;
    private BittrexAPI bittrexAPI;
    //private BittrexDatabase bittrexDatabase;

    public static BittrexRepository getInstance(){
        if(bittrexRepository == null){
            bittrexRepository = new BittrexRepository();
        }
        return bittrexRepository;
    }
    public BittrexRepository(){
        bittrexAPI = RetrofitService.getClient().create(BittrexAPI.class);
    }

    /*public BittrexRepository(Context context){
        bittrexDatabase = Room.databaseBuilder(context,BittrexDatabase.class,"AppDatabase").build();
    }*/

    public MutableLiveData<BittrexPOJO> getData(){
        final MutableLiveData<BittrexPOJO> bittrexPOJOMutableLiveData = new MutableLiveData<>();
        bittrexAPI.getData().enqueue(new Callback<BittrexPOJO>() {
            @Override
            public void onResponse(Call<BittrexPOJO> call, Response<BittrexPOJO> response) {
                if(response.isSuccessful()){
                    bittrexPOJOMutableLiveData.setValue(response.body());
                    /*if (response.body() != null) {
                        for(int i=0;i<response.body().getResult().size();i++){
                            bittrexDatabase.bittrexDAO.insertData(new Result(
                                    response.body().getResult().get(i).getCurrency(),
                                    response.body().getResult().get(i).getCurrencyLong(),
                                    response.body().getResult().get(i).getTxFee()
                            ));
                        }
                    }*/
                }
            }

            @Override
            public void onFailure(Call<BittrexPOJO> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return bittrexPOJOMutableLiveData;
    }

   /* public List<Result> fetchList(){
        return bittrexDatabase.bittrexDAO.fetchData();
    }*/

}
