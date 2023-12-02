package com.gachon.tw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class change_pswd extends AppCompatActivity {
    private EditText idInputEditText;
    private EditText newPasswordEditText;
    private EditText confirmPasswordEditText;
    private Button confirmButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pswd);

        idInputEditText = findViewById(R.id.input_id);
        newPasswordEditText = findViewById(R.id.input_name);
        confirmPasswordEditText = findViewById(R.id.input_tel);
        confirmButton = findViewById(R.id.Login_login_button);
        backButton = findViewById(R.id.Login_back_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 확인 버튼 클릭 시 동작
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(change_pswd.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
