package com.gachon.tw2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends AppCompatActivity {

    private EditText idEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        idEditText = findViewById(R.id.Login_idData);
        passwordEditText = findViewById(R.id.Login_passwordData);
        loginButton = findViewById(R.id.Login_login_button);
        backButton = findViewById(R.id.Login_back_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 로그인 처리를 수행하는 AsyncTask 실행
                new LoginAsyncTask().execute(id, password);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back 버튼을 눌렀을 때 메인 화면으로 이동
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    class LoginAsyncTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String id = params[0];
            String password = params[1];
            Connection connection = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://10.0.2.2:3306/modelDB",
                        "root", "1234");

                if (connection != null) {
                    Toast.makeText(login.this, "연결", Toast.LENGTH_SHORT).show();
                }

                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE user_id = ? AND password = ?");
                statement.setString(1, id);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                // 로그인 결과 반환
                boolean loginSuccess = resultSet.next();

                resultSet.close();
                statement.close();
                connection.close();

                return loginSuccess;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean loginSuccess) {
            if (loginSuccess) {
                // 로그인 성공
                Toast.makeText(login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, board.class);
                intent.putExtra("id", idEditText.getText().toString());
                startActivity(intent);
            } else {
                // 로그인 실패
                Toast.makeText(login.this, "아이디 또는 비밀번호가 잘못되었습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
}