package com.example.draftcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        welcomeButton = findViewById(R.id.welcomeButton);

        welcomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    SecondActivity.this,
                    HomeActivity.class
            );

            startActivity(intent);
        });
    }
}