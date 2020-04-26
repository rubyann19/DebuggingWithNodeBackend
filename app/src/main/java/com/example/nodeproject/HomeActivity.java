package com.example.nodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nodeproject.Retrofit.ApiService;
import com.example.nodeproject.Retrofit.RetrofitClient;
import com.example.nodeproject.ViewRecords.ViewAllRecordsActivity;

public class HomeActivity extends AppCompatActivity {
    private Button updateRecord, deleteRecord, viewAllRecords, searchRecord;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        updateRecord = findViewById(R.id.updateRecord);
        deleteRecord = findViewById(R.id.deleteRecord);
        viewAllRecords = findViewById(R.id.viewAllRecord);
        searchRecord = findViewById(R.id.searchRecord);
        logout = findViewById(R.id.logout);

        updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Loads the update screen", Toast.LENGTH_SHORT).show();
            }
        });
        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Loads the delete screen", Toast.LENGTH_SHORT).show();
            }
        });
        viewAllRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ViewAllRecordsActivity.class));
            }
        });
        searchRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Loads the search screen", Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                Toast.makeText(HomeActivity.this, "User was successfully logged out...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
