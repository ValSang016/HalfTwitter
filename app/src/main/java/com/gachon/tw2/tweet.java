package com.gachon.tw2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class tweet extends AppCompatActivity {
    private EditText editText;
    private TextView counter;
    private Button backButton;
    private Button tweetButton;

    private String id; // 사용자 아이디
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet);

        editText = findViewById(R.id.edit_text);
        counter = findViewById(R.id.counter);
        backButton = findViewById(R.id.btn_back);
        tweetButton = findViewById(R.id.tweet_button);
        id = getIntent().getStringExtra("id");

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
                new PostTweetAsyncTask().execute(id, editText.getText().toString());
            }
        });
    }
    class PostTweetAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String id = params[0];
            String tweet = params[1];
            Connection connection = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/modelDB",
                        "root", "1234");

                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO post (user_id, content) VALUES (?, ?)");
                statement.setString(1, id);
                statement.setString(2, tweet);
                statement.executeUpdate();

                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // 트윗 성공
            Toast.makeText(tweet.this, "트윗 성공", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(tweet.this, board.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}
