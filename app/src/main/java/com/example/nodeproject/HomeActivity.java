package com.example.nodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {
    private Button updateRecord, deleteRecord, viewAllRecords, searchRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        updateRecord = findViewById(R.id.updateRecord);
        deleteRecord = findViewById(R.id.deleteRecord);
        viewAllRecords = findViewById(R.id.viewAllRecord);
        searchRecord = findViewById(R.id.searchRecord);
        ApiService api = RetrofitClient.getApiService();

        updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, UpdateRecordActivity.class));
            }
        });
        deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DeleteRecordActivity.class));
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
                startActivity(new Intent(HomeActivity.this, SearchRecordActivity.class));
            }
        });
    }
}
