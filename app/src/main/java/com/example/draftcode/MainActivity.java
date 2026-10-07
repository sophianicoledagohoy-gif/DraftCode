package com.example.draftcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button superButton;
    Button superButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // LOGIN BUTTON
        superButton = findViewById(R.id.superButton);

        superButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        // CLEAR BUTTON
        superButton2 = findViewById(R.id.superButton2);

        superButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username.setText("");
                password.setText("");

            }
        });
    }
}