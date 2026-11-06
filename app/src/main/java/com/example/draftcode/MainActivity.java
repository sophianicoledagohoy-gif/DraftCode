
package com.example.draftcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    Button superButton;
    Button superButton2;
    Button registerButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        // LOGIN BUTTON
        superButton = findViewById(R.id.superButton);

        superButton.setOnClickListener(v -> {

            String email = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this,
                        "Please enter your email and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(this,
                                    "Login successful!",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(
                                    MainActivity.this,
                                    SecondActivity.class
                            );

                            startActivity(intent);
                            finish();

                        } else {

                            String errorMessage =
                                    "Please check your email and password.";

                            if (task.getException() != null) {
                                errorMessage =
                                        task.getException().getMessage();
                            }

                            Toast.makeText(this,
                                    "Login failed: " + errorMessage,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // CLEAR BUTTON
        superButton2 = findViewById(R.id.superButton2);

        superButton2.setOnClickListener(v -> {
            username.setText("");
            password.setText("");
        });

        // REGISTER BUTTON
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    RegisterActivity.class
            );

            startActivity(intent);
        });
    }
}
