
package com.example.draftcode;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, registerPassword;
    Button createAccountButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(v -> {

            String email = registerEmail.getText().toString().trim();
            String password = registerPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this,
                        "Password must be at least 6 characters",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            Toast.makeText(this,
                                    "Account created successfully!",
                                    Toast.LENGTH_SHORT).show();

                            finish();

                        } else {

                            String errorMessage =
                                    "Please check your email and password.";

                            if (task.getException() != null) {
                                errorMessage =
                                        task.getException().getMessage();
                            }

                            Toast.makeText(this,
                                    "Registration failed: " + errorMessage,
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}
