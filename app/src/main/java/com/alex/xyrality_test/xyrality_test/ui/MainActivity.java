package com.alex.xyrality_test.xyrality_test.ui;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alex.xyrality_test.xyrality_test.R;
import com.alex.xyrality_test.xyrality_test.adapters.WorldAdapter;
import com.alex.xyrality_test.xyrality_test.fragments.RetainedFragment;
import com.alex.xyrality_test.xyrality_test.rest.RestClient;
import com.alex.xyrality_test.xyrality_test.rest.results.World;
import com.alex.xyrality_test.xyrality_test.rest.results.WorldsResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email;
    private EditText password;
    private Button sendDataBtn;
    private RelativeLayout credentialsRL;
    private ListView worldsLV;
    private RetainedFragment retainedFragment;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        initListeners();
        initProgress();
        checkScreenRotation();
    }

    private void initUi() {
        email = (EditText) findViewById(R.id.emailEt);
        password = (EditText) findViewById(R.id.passEt);
        sendDataBtn = (Button) findViewById(R.id.sendDataBtn);
        credentialsRL = (RelativeLayout) findViewById(R.id.credentialsRl);
        worldsLV = (ListView) findViewById(R.id.worldsLv);
    }

    private void initListeners() {
        sendDataBtn.setOnClickListener(this);
    }

    private void initProgress() {
        progress = new ProgressDialog(this);
        progress.setTitle(getString(R.string.please_wait));
        progress.setMessage(getString(R.string.worlds_loading));
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void checkScreenRotation() {
        FragmentManager fm = getFragmentManager();
        retainedFragment = (RetainedFragment) fm.findFragmentByTag(RetainedFragment.TAG);
        if (retainedFragment == null) {
            retainedFragment = new RetainedFragment();
            fm.beginTransaction().add(retainedFragment, RetainedFragment.TAG).commit();

        } else {
            World[] worlds = retainedFragment.getWorlds();
            showWorlds(worlds);
        }
    }

    private void loginUserToServer() {
        showProgress();
        String login = getLogin();
        String password = getPassword();
        String deviceType = getDeviceType();
        String deviceId = getDeviceId();
        RestClient.INSTANCE.getRestApi().getWorlds(login, password, deviceType, deviceId, mCallback);
    }

    private void showProgress() {
        progress.show();
    }

    private void hideProgress() {
        if (progress.isShowing())
            progress.dismiss();
    }

    private String getLogin() {
        if (!TextUtils.isEmpty(email.getText().toString())) {
            return email.getText().toString();
        }

        return "android.test@xyrality.com";
    }

    private String getPassword() {
        if (!TextUtils.isEmpty(password.getText().toString())) {
            return password.getText().toString();
        }

        return "defaultPassrord";
    }

    private String getDeviceType() {
        String model = android.os.Build.MODEL;
        String version = android.os.Build.VERSION.RELEASE;

        return String.format("%s %s", model, version);
    }

    private String getDeviceId() {
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();

        return info.getMacAddress();
    }

    private Callback<WorldsResult> mCallback = new Callback<WorldsResult>() {

        @Override
        public void success(WorldsResult worldsResult, Response response) {
            hideProgress();
            World[] worlds = worldsResult.getWorlds();
            saveWorldsInFragment(worlds);
            showWorlds(worlds);
        }

        @Override
        public void failure(RetrofitError error) {
            hideProgress();
            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
        }
    };

    private void saveWorldsInFragment(World[] worlds) {
        retainedFragment.setWorlds(worlds);
    }

    private void showWorlds(World[] worlds) {
        hideCredentials();
        showList();
        setUpList(worlds);
    }

    private void hideCredentials() {
        credentialsRL.setVisibility(View.GONE);
    }

    private void showList() {
        worldsLV.setVisibility(View.VISIBLE);
    }

    private void setUpList(World[] worlds) {
        BaseAdapter worldsAdapter = new WorldAdapter(this, worlds);
        worldsLV.setAdapter(worldsAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendDataBtn:
                loginUserToServer();
                break;
        }
    }
}
