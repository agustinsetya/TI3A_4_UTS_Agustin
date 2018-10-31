package com.example.user.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {
    SessionManagement sessionManagement;
    TextView tvUser;
    Button buttonDB;
    HashMap<String,String> loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvUser = (TextView) findViewById(R.id.tvUser);
        buttonDB = (Button) findViewById(R.id.buttonDB);

        sessionManagement = new SessionManagement(this);
        loginUser = sessionManagement.getUserInformation();

        Toast.makeText(this, sessionManagement.isLoggedIn()+"", Toast.LENGTH_LONG).show();

        tvUser.setText("Selamat Datang, "+loginUser.get(sessionManagement.KEY_USERNAME));

        buttonDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, InputKota.class);
                startActivity(intent);
            }
        });
    }
}
