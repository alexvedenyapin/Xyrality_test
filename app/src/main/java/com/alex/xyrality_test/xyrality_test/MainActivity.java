package com.alex.xyrality_test.xyrality_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button sendDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        email = (EditText)findViewById(R.id.emailEt);
        password = (EditText)findViewById(R.id.passEt);
        sendDataBtn = (Button)findViewById(R.id.sendDataBtn);
    }
}
