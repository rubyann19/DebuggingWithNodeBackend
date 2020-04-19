package com.example.nodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText firstName, lastName;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService api = RetrofitClient.getApiService();
                Call<List<GeneralResponse>> userCall = api.checkUserCredentials(firstName.getText().toString(), lastName.getText().toString());

                userCall.enqueue(new Callback<List<GeneralResponse>>() {
                    @Override
                    public void onResponse(Call<List<GeneralResponse>> call, Response<List<GeneralResponse>> response) {
                        if (response.code() == 200) {
                            GeneralResponse resp = response.body().get(0);
                            if (resp.getStatus() == 200){
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
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
    }
}
