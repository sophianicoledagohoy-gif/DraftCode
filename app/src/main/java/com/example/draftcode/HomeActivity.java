package com.example.draftcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    TextView userEmail;
    Button aboutButton, profileButton, helpButton, logoutButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.userEmail);
        aboutButton = findViewById(R.id.aboutButton);
        profileButton = findViewById(R.id.profileButton);
        helpButton = findViewById(R.id.helpButton);
        logoutButton = findViewById(R.id.logoutButton);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            userEmail.setText(getString(R.string.logged_in_email, user.getEmail()));
        } else {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
            return;
        }

        aboutButton.setOnClickListener(v ->
                showMessage("About DraftCode",
                        "DraftCode is a simple personal space app created to demonstrate Android development and Firebase Authentication.")
        );

        profileButton.setOnClickListener(v ->
                showMessage(
                        "My Profile",
                        getString(R.string.profile_email, user.getEmail())
                )
        );

        helpButton.setOnClickListener(v ->
                showMessage("Help & Support",
                        "Welcome to DraftCode! For help using this app, please contact the app developer.")
        );

        logoutButton.setOnClickListener(v -> {
            mAuth.signOut();

            Toast.makeText(HomeActivity.this,
                    "You have logged out.",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}