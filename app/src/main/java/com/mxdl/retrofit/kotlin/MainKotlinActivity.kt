package com.mxdl.retrofit.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mxdl.retrofit.R
import com.mxdl.retrofit.kotlin.entity.Token
import com.mxdl.retrofit.kotlin.entity.User
import com.mxdl.retrofit.kotlin.manager.RetrofitManager
import com.mxdl.retrofit.kotlin.service.CommonService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main_kotlin.*

class MainKotlinActivity : AppCompatActivity(), View.OnClickListener{
    var commonService: CommonService? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin)
        btn_token1.setOnClickListener(this)
        btn_create_user1.setOnClickListener(this)
        commonService = RetrofitManager.getInstance()?.getCommonService()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_token1 -> {
                getToken()
            }
            R.id.btn_create_user1 ->{
                createUser()
            }
        }
    }

    private fun createUser() {
        commonService?.createUser(User("lisi", "123456"))
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<User> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: User) {

                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun getToken() {
        commonService?.getToken(
            "Basic bXhkbC1jbGllbnQ6bXhkbC1zZWNyZXQ=",
            "password",
            "mxdl",
            "123456"
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<Token> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Token) {
                    RetrofitManager.getInstance()?.token = t.access_token
                }

                override fun onError(e: Throwable) {
                }

            })
    }
}
