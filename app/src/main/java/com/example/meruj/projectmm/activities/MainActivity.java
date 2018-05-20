package com.example.meruj.projectmm.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.meruj.projectmm.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonReg,buttonLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonReg = findViewById(R.id.register);
        buttonLog = findViewById(R.id.login);
        SharedPreferences preferences = getSharedPreferences("com.example.meruj",MODE_PRIVATE);
        if(preferences.getBoolean("IsLogin",false)){
            Intent intent = new Intent(MainActivity.this,PageActivity.class);
            startActivity(intent);
        }
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("com.example.meruj.projectfull.activities.RegisterActivity");
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent("com.example.meruj.projectfull.activities.LoginActivity");
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
