package com.gachon.tw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class tweet extends AppCompatActivity {
    private EditText editText;
    private TextView counter;
    private Button backButton;
    private Button tweetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet);

        editText = findViewById(R.id.edit_text);
        counter = findViewById(R.id.counter);
        backButton = findViewById(R.id.btn_back);
        tweetButton = findViewById(R.id.tweet_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TweetActivity로 이동
                Intent intent = new Intent(tweet.this, board.class);
                startActivity(intent);
            }
        });

        tweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 트윗 버튼 클릭 시 동작
            }
        });
    }
}
