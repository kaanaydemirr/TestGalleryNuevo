package com.nuevo.testgallary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/photos")
    Call<List<Pojo>> getData();

    @POST("/comments")
    Call<ItemDetailResponse> getDetail(@Query("postId") Integer id);
}
