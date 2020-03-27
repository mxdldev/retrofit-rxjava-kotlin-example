package com.mxdl.retrofit.java;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mxdl.retrofit.R;
import com.mxdl.retrofit.java.service.CommonCallService;
import com.mxdl.retrofit.java.manager.RetrofitCallManager;
import com.mxdl.retrofit.java.entity.Token;
import com.mxdl.retrofit.java.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallActivity extends AppCompatActivity implements View.OnClickListener {
    private CommonCallService commonCallService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        findViewById(R.id.btn_net).setOnClickListener(this);
        findViewById(R.id.btn_create_user).setOnClickListener(this);

        commonCallService = RetrofitCallManager.getInstance(this).getCommonService();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_net:
                getToken();
                break;
            case R.id.btn_create_user:
                createUser();
                break;
        }
    }
    public void getToken() {
        Call<Token> tokenCall = commonCallService.getToken("Basic bXhkbC1jbGllbnQ6bXhkbC1zZWNyZXQ=", "password", "mxdl", "123456");
        tokenCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token body = response.body();
                if (body != null) {
                    RetrofitCallManager.getInstance(CallActivity.this).setToken(body.getAccess_token());
                    Log.v("MYTAG", body.toString());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });

    }

    private void createUser() {
        //创建一个创建用户的请求对象
        Call<User> createUserCall = commonCallService.createUser(new User("zhangsan", "1234560"));
        //执行这个请求任务
        createUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.v("MYTAG", "onResponse start...");
                User user = response.body();
                if (user != null) {
                    Log.v("MYTAG", user.toString());
                } else {
                    Log.v("MYTAG", "User create fail");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.v("MYTAG", "onFailure start...");
            }
        });
    }
}
