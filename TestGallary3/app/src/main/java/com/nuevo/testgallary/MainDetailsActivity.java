package com.nuevo.testgallary;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDetailsActivity extends AppCompatActivity {
    RecyclerView recycle;

    private void listingdetail(){
        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<ItemDetailResponse> listingdetail = apiInterface.getDetail(0);
        listingdetail.enqueue(new Callback<ItemDetailResponse>() {
            @Override
            public void onResponse(Call<ItemDetailResponse> call, Response<ItemDetailResponse> response) {
                if (response.isSuccessful()) ;
                {
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainDetailsActivity.this, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Integer id) {
                            Intent intent = new Intent();
                            intent.putExtra("id", id);
                            startActivity(intent);
                            Toast.makeText(MainDetailsActivity.this, ""+ id, Toast.LENGTH_SHORT);
                        }

                        private void startActivity(Intent intent) {
                            startActivity(intent);
                        }
                    }, response.body());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainDetailsActivity.this);
                    recycle.setLayoutManager(linearLayoutManager);
                    recycle.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ItemDetailResponse> call, Throwable t) {



            }
        });
    }
}

