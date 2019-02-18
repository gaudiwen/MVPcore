package com.example.rxjava_retrofit_mvp_md.login.widget;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjava_retrofit_mvp_md.R;
import com.example.rxjava_retrofit_mvp_md.beans.LoginBean;
import com.example.rxjava_retrofit_mvp_md.delegates.PlaneDelegate;
import com.example.rxjava_retrofit_mvp_md.login.presenter.LoginPresenterImpl;
import com.example.rxjava_retrofit_mvp_md.login.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginDelegate extends PlaneDelegate implements LoginView {

    @BindView(R.id.userLoginInputView)
    EditText mUserLoginInputView;
    @BindView(R.id.passwordInputView)
    EditText mPasswordInputView;
    @BindView(R.id.version)
    TextView mVersion;
    @BindView(R.id.loginButton)
    Button mLoginButton;
    private LoginPresenterImpl loginPresenterImpl;

    public static LoginDelegate newInstance() {

        Bundle args = new Bundle();

        LoginDelegate fragment = new LoginDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    protected void initData(Bundle arguments) {

        loginPresenterImpl = new LoginPresenterImpl(this, this);
    }

    @Override
    protected void init() {

        initView();
    }

    private void initView() {

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterImpl.Login(mUserLoginInputView.getText().toString(),mPasswordInputView.getText().toString());
            }
        });
    }

    @Override
    public void login(LoginBean loginBean) {
        Toast.makeText(getActivity(),"登录成功获取到数据",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg(Throwable e) {

    }
}
