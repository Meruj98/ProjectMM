package com.example.meruj.projectmm.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meruj.projectmm.database.DbHelper;
import com.example.meruj.projectmm.R;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private EditText email, password;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.button2);
        email = findViewById(R.id.log);
        password = findViewById(R.id.pass);

        dbHelper = new DbHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "Please write your password", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent = new Intent("com.example.meruj.projectfull.activities.PageActivity");

                    if (dbHelper.ifUserAdded(email.getText().toString())) {
                        if (dbHelper.ifPasswordTrue(email.getText().toString(), password.getText().toString())) {
                            SharedPreferences preferences = getSharedPreferences("com.example.meruj", MODE_PRIVATE);
                            preferences.edit().putBoolean("IsLogin", true).apply();
                            preferences.edit().putString("LoginUserName", email.getText().toString()).apply();
                            Intent intent = new Intent(LoginActivity.this, PageActivity.class);
                            finish();
                            startActivity(intent);
                        } else {

                            Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "This email is not recognized ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
