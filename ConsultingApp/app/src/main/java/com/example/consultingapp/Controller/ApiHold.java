package com.example.consultingapp.Controller;

import com.example.consultingapp.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHold {
    @POST("login")
    Call<User> login(@Body User u);
    @PATCH("utilisateurs/{password}")
    Call<User> updatePassword(@Path("password") String password , @Body User u);
    @POST("utilisateurs")
    Call<User> signin(@Body User u);
    @PATCH("utilisateurs/{subs}")
    Call<User> updateSubscription(@Path("subs") String subs , @Body User u);
    @GET("utilisateurs/{id}/")
    Call <User> getUserInfo(@Path("id") String UserID);

}
