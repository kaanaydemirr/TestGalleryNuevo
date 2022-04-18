package com.nuevo.testgallary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle = findViewById(R.id.recycle);

        listingdata();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recycle.setLayoutManager(linearLayoutManager);
    }

    private void listingdata() {
        ApiInterface apiInterface = Retrofit.getRetrofit().create(ApiInterface.class);
        Call<List<Pojo>> listingdata = apiInterface.getData();
        listingdata.enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {
                if (response.isSuccessful()) ;
                {
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, new OnItemClickListener() {
                        @Override
                        public void onItemClick(Integer id) {
                            Intent intent = new Intent(MainActivity.this, MainDetailsActivity.class);
                            intent.putExtra("id", id);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, ""+id, Toast.LENGTH_LONG).show();
                        }
                    }, response.body());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recycle.setLayoutManager(linearLayoutManager);
                    recycle.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });
    }
}

