package com.example.nodeproject.ViewRecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nodeproject.Retrofit.ApiService;
import com.example.nodeproject.R;
import com.example.nodeproject.Retrofit.RetrofitClient;
import com.example.nodeproject.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllRecordsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<User> userList = new ArrayList<>();
    private ViewRecordsAdapter adapter;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_records);

        recyclerView = findViewById(R.id.recyclerView);
        back = findViewById(R.id.back);
        ApiService api = RetrofitClient.getApiService();
        Call<List<User>> allUsersCall = api.getAllUsers();
        allUsersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userList = response.body();

                adapter = new ViewRecordsAdapter(ViewAllRecordsActivity.this, userList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewAllRecordsActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ViewAllRecordsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
