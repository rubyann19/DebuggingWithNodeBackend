package com.example.nodeproject.Retrofit;

import com.example.nodeproject.GeneralResponse;
import com.example.nodeproject.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("getUser/{id}")
    Call<List<User>> getParticularUser(@Path("id") int id);
    @GET("getAllUsers")
    Call<List<User>> getAllUsers();
    @FormUrlEncoded
    @POST("registerUser")
    Call<GeneralResponse> registerUser(@Field("email_address") String email, @Field("full_name") String fullName, @Field("password") String password, @Field("phone_number") String phone);
    @FormUrlEncoded
    @POST("addUser")
    Call<GeneralResponse> addUser(@Field("email") String email, @Field("last_name") String password, @Field("name") String name, @Field("phone") String phone);
    @FormUrlEncoded
    @PUT("updateUser")
    Call<GeneralResponse> updateUser(@Field("email_address") String email, @Field("full_name") String fullName, @Field("password") String password, @Field("phone_number") String phone, @Field("id") int id);
    @FormUrlEncoded
    @POST("checkUserCredentials")
    Call<List<GeneralResponse>> checkUserCredentials(@Field("email_address") String email, @Field("password") String password);
    @FormUrlEncoded
    @PUT("deleteUser")
    Call<GeneralResponse> deleteUser(@Field("id") int id);
}