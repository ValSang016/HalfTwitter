package com.gachon.tw2;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class board extends AppCompatActivity {
    private Button tweetButton;
    private Button settingButton;
    private ImageButton searchButton; // 돋보기 버튼 추가

    private TableLayout tableLayout; // 게시물을 추가할 TableLayout
    private String id; // 사용자 아이디

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);



        tweetButton = findViewById(R.id.tweet_button);
        settingButton = findViewById(R.id.setting_button);
        searchButton = findViewById(R.id.search_btn); // 돋보기 버튼 아이디 연결
        tableLayout = findViewById(R.id.table_layout);

        id = getIntent().getStringExtra("id");
        new GetPostsAsyncTask().execute(id);

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
    class GetPostsAsyncTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            String id = params[0];
            Connection connection = null;
            List<String> posts = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/modelDB",
                        "root", "1234");

                PreparedStatement statement = connection.prepareStatement(
                        "SELECT content FROM post WHERE user_id = ?");
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();

                // 사용자의 모든 게시물을 가져옵니다.
                while (resultSet.next()) {
                    posts.add(resultSet.getString("post"));
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return posts;
        }

        @Override
        protected void onPostExecute(List<String> posts) {
            for (String post : posts) {
                // 각 게시물에 대해 행을 생성하고 테이블 레이아웃에 추가합니다.
                TableRow tableRow = new TableRow(board.this);
                TextView textView = new TextView(board.this);
                textView.setText(post);
                textView.setBackground(ContextCompat.getDrawable(board.this, R.drawable.border));
                tableRow.addView(textView);
                tableLayout.addView(tableRow);
            }
        }
    }
}
