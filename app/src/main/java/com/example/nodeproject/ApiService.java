package com.example.nodeproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("getUser/{id}")
    Call<List<User>> getParticularUser(@Path("id") int id);
    @GET("getUsers")
    Call<List<User>> getAllUsers();
    @FormUrlEncoded
    @POST("registerUser")
    Call<GeneralResponse> registerUser(@Field("first_name") String firstName, @Field("last_name") String lastName);
    @FormUrlEncoded
    @POST("addUser")
    Call<GeneralResponse> addUser(@Field("first_name") String firstName, @Field("last_name") String lastName);
    @FormUrlEncoded
    @PUT("updateUser")
    Call<GeneralResponse> updateUser(@Field("first_name") String firstName, @Field("id") int id);
    @FormUrlEncoded
    @POST("checkUserCredentials")
    Call<List<GeneralResponse>> checkUserCredentials(@Field("first_name") String firstName, @Field("last_name") String lastName);
    @FormUrlEncoded
    @PUT("deleteUser")
    Call<GeneralResponse> deleteUser(@Field("id") int id);
}
