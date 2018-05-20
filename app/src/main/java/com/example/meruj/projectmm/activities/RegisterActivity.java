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
import com.example.meruj.projectmm.user_utility.User;

public class RegisterActivity extends AppCompatActivity {
    private Button button;
    private EditText name, surname, email, password, number;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = findViewById(R.id.button);
        name = findViewById(R.id.name);
//        surname = findViewById(R.id.surname);

        db = new DbHelper(this);

        email = findViewById(R.id.mail);
        password = findViewById(R.id.password);
//        number = findViewById(R.id.number);
        final char[] c = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = true;

                if (name.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please,enter your name", Toast.LENGTH_SHORT).show();
//                } else if (surname.getText().toString().length() == 0) {
//                    Toast.makeText(RegisterActivity.this, "Please,enter your Surname", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().length() == 0
                         /*email.getText().toString().endsWith("@mail.ru")
                        || email.getText().toString().endsWith("@gmail.com")
                        || email.getText().toString().endsWith("@yandex.ru")
                        || email.getText().toString().endsWith("@rambler.ru")*/) {

                    Toast.makeText(RegisterActivity.this, "E-mail you entered isn't correct", Toast.LENGTH_SHORT).show();

                } else if (password.getText().toString().length() <= 4) {
                    Toast.makeText(RegisterActivity.this, "Password  must contain more than 4 letter",
                            Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().contains(c.toString())) {
                    Toast.makeText(RegisterActivity.this, "Password cannot contain any symbol",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (!db.ifUserAdded(email.getText().toString())) {
                        db.addUser(new User(name.getText().toString(), email.getText().toString(), password.getText().toString()));
                        SharedPreferences preferences = getSharedPreferences("com.example.meruj", MODE_PRIVATE);
                        preferences.edit().putBoolean("IsLogin", true).apply();
                        preferences.edit().putString("LoginUserName", email.getText().toString()).apply();
//                    Intent intent = new Intent("com.example.meruj.project.PageActivity");
                        Intent intent = new Intent(RegisterActivity.this, PageActivity.class);
                        finish();

                        startActivity(intent);

                    } else {
                        Toast.makeText(RegisterActivity.this, "This mail exist",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}
