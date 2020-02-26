package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.recyclerview.MainActivity.EXTRA_ID;
import static com.example.recyclerview.MainActivity.EXTRA_NAME;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String username = intent.getStringExtra(EXTRA_NAME);
        int userId = intent.getIntExtra(EXTRA_ID, 0);

        TextView tvName = findViewById(R.id.tv_detailname);
        TextView tvID = findViewById(R.id.tv_detailId);

        tvName.setText("Name: " +username);
        tvID.setText("Id: " + userId);
    }
}
