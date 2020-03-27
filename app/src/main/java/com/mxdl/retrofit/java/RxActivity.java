package com.mxdl.retrofit.java;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mxdl.retrofit.R;
import com.mxdl.retrofit.api.entity.Token;
import com.mxdl.retrofit.api.entity.User;
import com.mxdl.retrofit.java.api.service.CommonRxService;
import com.mxdl.retrofit.java.api.manager.RetrofitRxManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity implements View.OnClickListener {
    private CommonRxService commonCallService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        findViewById(R.id.btn_net).setOnClickListener(this);
        findViewById(R.id.btn_create_user).setOnClickListener(this);

        commonCallService = RetrofitRxManager.getInstance(this).getCommonRxService();
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
        Observable<Token> tokenCall = commonCallService.getToken("Basic bXhkbC1jbGllbnQ6bXhkbC1zZWNyZXQ=", "password", "mxdl", "123456");
        tokenCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Token value) {
                if (value != null) {
                    RetrofitRxManager.getInstance(RxActivity.this).setToken(value.getAccess_token());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void createUser() {
        //创建一个创建用户的请求对象
        Observable<User> createUserCall = commonCallService.createUser(new User("zhangsan", "1234560"));
        createUserCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
