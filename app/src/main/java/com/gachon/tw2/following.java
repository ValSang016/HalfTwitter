package com.gachon.tw2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class following extends AppCompatActivity {
    private TableLayout tableLayout; // 팔로워를 추가할 TableLayout
    private String id; // 사용자 아이디

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.following);

        tableLayout = findViewById(R.id.table_layout);

        // Intent에서 아이디를 가져옵니다.
        id = getIntent().getStringExtra("id");

        // 팔로워를 가져와서 행을 생성하고 테이블 레이아웃에 추가하는 코드
        new GetFollowersAsyncTask().execute(id);
    }

    class GetFollowersAsyncTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            String id = params[0];
            Connection connection = null;
            List<String> followers = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/modelDB",
                        "root", "1234");

                PreparedStatement statement = connection.prepareStatement(
                        "SELECT followed_id FROM follow WHERE following_id = ?");
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();

                // 사용자의 모든 팔로워를 가져옵니다.
                while (resultSet.next()) {
                    followers.add(resultSet.getString("follower_id"));
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return followers;
        }

        @Override
        protected void onPostExecute(List<String> followers) {
            for (String follower : followers) {
                // 각 팔로워에 대해 행을 생성하고 테이블 레이아웃에 추가합니다.
                TableRow tableRow = new TableRow(following.this);
                TextView textView = new TextView(following.this);
                textView.setText(follower);
                tableRow.addView(textView);
                tableLayout.addView(tableRow);
            }
        }
    }
}

