package com.gachon.tw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class registeration extends AppCompatActivity {
    private EditText nameEditText;
    private EditText birthEditText;
    private EditText idEditText;
    private EditText passwordEditText;

    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private ImageView userImageView;
    private Button uploadPictureButton;
    private Button nextButton;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        nameEditText = findViewById(R.id.name);
        birthEditText = findViewById(R.id.birth);
        idEditText = findViewById(R.id.id_write);
        passwordEditText = findViewById(R.id.password_write);
        phoneNumberEditText = findViewById(R.id.phonenumber_write);
        emailEditText = findViewById(R.id.phonenumber_write2);
        userImageView = findViewById(R.id.user_image);
        uploadPictureButton = findViewById(R.id.btn_UploadPicture);
        nextButton = findViewById(R.id.go_to_login);
        backButton = findViewById(R.id.back_button);


        uploadPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진 선택 버튼 클릭 시 동작
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다음 버튼 클릭 시 동작
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registeration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
