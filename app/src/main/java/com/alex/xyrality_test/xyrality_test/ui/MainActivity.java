package com.alex.xyrality_test.xyrality_test.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alex.xyrality_test.xyrality_test.R;
import com.alex.xyrality_test.xyrality_test.rest.RestClient;
import com.alex.xyrality_test.xyrality_test.rest.results.WorldsResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private Button sendDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initListeners();
    }

    private void initUi() {
        email = (EditText) findViewById(R.id.emailEt);
        password = (EditText) findViewById(R.id.passEt);
        sendDataBtn = (Button) findViewById(R.id.sendDataBtn);
    }

    private void initListeners() {
        sendDataBtn.setOnClickListener(this);
    }

    private void loginUserToServer() {
        String login = "android1.test@xyrality.com";
        String password = "123456qwertyu";
        String deviceType = "Android.Nexus61";
        String deviceId = "23464677893256873";
//        GetWorldsRequest getWorlds = new GetWorldsRequest(login, password, deviceType, deviceId);
//        String input = new Gson().toJson(getWorlds);
        RestClient.INSTANCE.getRestApi().getWorlds(login, password, deviceType, deviceId, mCallback);
//            mProgressDialog.show();
    }

    private Callback<WorldsResult> mCallback = new Callback<WorldsResult>() {

        @Override
        public void success(WorldsResult worldsResult, Response response) {
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendDataBtn:
                loginUserToServer();
                break;
        }
    }
}