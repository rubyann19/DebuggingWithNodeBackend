package com.example.nodeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nodeproject.Model.User;
import com.example.nodeproject.Retrofit.ApiService;
import com.example.nodeproject.Retrofit.RetrofitClient;
import com.example.nodeproject.ViewRecords.ViewAllRecordsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateRecordActivity extends AppCompatActivity {
    private ImageView back;
    private EditText name, phone, email, password;
    private Button editUser;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        editUser = findViewById(R.id.editUser);
        id = getIntent().getIntExtra("ID", 0);

        ApiService api = RetrofitClient.getApiService();
        Call<List<User>> userCall = api.getParticularUser(id);
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        User user = response.body().get(0);

                        name.setText(user.getName());
                        phone.setText(user.getPhone());
                        email.setText(user.getEmail());
                        password.setText(user.getPassword());
                    } else
                        Toast.makeText(UpdateRecordActivity.this, "There was some issue in getting the user information...", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(UpdateRecordActivity.this, "There was some problem in view the user info...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UpdateRecordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService api = RetrofitClient.getApiService();
                Call<GeneralResponse> call = api.updateUser(email.getText().toString(), name.getText().toString(), password.getText().toString(), phone.getText().toString(), id);

                call.enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.body() != null) {
                            GeneralResponse genResponse = response.body();

                            if (genResponse.getStatus() == 200) {
                                Toast.makeText(UpdateRecordActivity.this, "The user was updated successfully...", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UpdateRecordActivity.this, ViewAllRecordsActivity.class));
                            }
                        } else {
                            Toast.makeText(UpdateRecordActivity.this, "There was an issue in updating the user...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(UpdateRecordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
