package com.example.meruj.projectmm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.meruj.projectmm.R;

public class User_Activity extends AppCompatActivity {

    private TextView text1, text2;
    private Button call,sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        sms = findViewById(R.id.sms);
        call = findViewById(R.id.call);
        String name = getIntent().getStringExtra("1");
        final String number = getIntent().getStringExtra("3");
        text1.setText(name);
        text2.setText(number);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialContacPhone(number);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(User_Activity.this);
                builder.setTitle("SMS");
                builder.setMessage("ENTER TEXT");
                final EditText input = new EditText(User_Activity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                                + number)));
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + input));
                        intent.putExtra("sms_body", input.getText().toString());
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });
    }
    private void dialContacPhone(String phoneNumber){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phoneNumber,null)));
    }
    private  void smsContacPhone(String phoneNumber){
        startActivity(new Intent(Intent.ACTION_SEND,Uri.fromParts("sms",phoneNumber,null)));
    }
}
