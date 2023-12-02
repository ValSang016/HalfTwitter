package com.gachon.tw2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button findPwButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        findPwButton = findViewById(R.id.Main_findPw_button);
        signUpButton = findViewById(R.id.Main_signUp_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        findPwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ChangePasswordActivity
                Intent intent = new Intent(MainActivity.this, change_pswd.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RegistrationActivity
                Intent intent = new Intent(MainActivity.this, registeration.class);
                startActivity(intent);
            }
        });
    }
}
