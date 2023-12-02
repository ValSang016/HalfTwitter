package com.gachon.tw2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class board extends AppCompatActivity {
    private ListView tweetList;
    private Button tweetButton;
    private Button settingButton;
    private ImageButton searchButton; // 돋보기 버튼 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        tweetList = findViewById(R.id.tweet_list);
        tweetButton = findViewById(R.id.tweet_button);
        settingButton = findViewById(R.id.setting_button);
        searchButton = findViewById(R.id.search_btn); // 돋보기 버튼 아이디 연결

        tweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TweetActivity로 이동
                Intent intent = new Intent(board.this, tweet.class);
                startActivity(intent);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 설정 버튼 클릭 시 동작
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SearchActivity로 이동
                Intent intent = new Intent(board.this, search.class);
                startActivity(intent);
            }
        });
    }
}
