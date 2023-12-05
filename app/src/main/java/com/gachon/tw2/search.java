package com.gachon.tw2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class search extends AppCompatActivity {
    private EditText searchText;
    private TableLayout tableLayout; // 검색 결과를 추가할 TableLayout
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        searchText = findViewById(R.id.search_id);
        tableLayout = findViewById(R.id.table_layout);

        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색 버튼 클릭 시 동작
                String query = searchText.getText().toString();
                new SearchAsyncTask().execute(query);
            }
        });
    }

    class SearchAsyncTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            String query = params[0];
            Connection connection = null;
            List<String> ids = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/modelDB",
                        "root", "1234");

                PreparedStatement statement = connection.prepareStatement(
                        "SELECT user_id FROM user WHERE user_id LIKE ?");
                statement.setString(1, "%" + query + "%");
                ResultSet resultSet = statement.executeQuery();

                // 검색 결과를 가져옵니다.
                while (resultSet.next()) {
                    ids.add(resultSet.getString("user_id"));
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return ids;
        }

        @Override
        protected void onPostExecute(List<String> ids) {
            tableLayout.removeAllViews(); // 기존 검색 결과를 제거합니다.

            for (String id : ids) {
                // 각 검색 결과에 대해 행을 생성하고 테이블 레이아웃에 추가합니다.
                TableRow tableRow = new TableRow(search.this);
                TextView textView = new TextView(search.this);
                textView.setText(id);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 텍스트뷰 클릭 시 other_board 액티비티로 이동
                        Intent intent = new Intent(search.this, others_board.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
                tableRow.addView(textView);
                tableLayout.addView(tableRow);
            }
        }
    }
}


