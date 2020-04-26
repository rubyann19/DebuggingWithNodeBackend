package com.example.nodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nodeproject.Retrofit.ApiService;
import com.example.nodeproject.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService api = RetrofitClient.getApiService();
                //Send request to server at runtime
                Call<List<GeneralResponse>> userCall = api.checkUserCredentials(email.getText().toString(), password.getText().toString());

                //Receive response from the server at runtime
                userCall.enqueue(new Callback<List<GeneralResponse>>() {
                    @Override
                    public void onResponse(Call<List<GeneralResponse>> call, Response<List<GeneralResponse>> response) {
                        if (response.code() == 200) {
                            GeneralResponse resp = response.body().get(0);
                            if (resp.getStatus() == 200){
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            try {
                                String error = response.errorBody().string();
                                System.out.println("Error occurred: \n" + error);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GeneralResponse>> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}
