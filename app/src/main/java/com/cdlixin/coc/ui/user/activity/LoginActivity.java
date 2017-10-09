package com.cdlixin.coc.ui.user.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdlixin.coc.R;
import com.cdlixin.coc.global.BaseActivity;
import com.cdlixin.coc.presenter.user.impl.LoginPresenter;
import com.cdlixin.coc.ui.common.widget.TitleBar;
import com.cdlixin.coc.ui.user.view.LoginView;
import com.cdlixin.coc.utils.ToastUtils;

import butterknife.Bind;

public class LoginActivity extends BaseActivity<LoginView,LoginPresenter> implements LoginView{

    @Bind(R.id.titleBar)
    TitleBar titleBar;
    @Bind(R.id.img_userhead)
    ImageView imgUserhead;
    @Bind(R.id.et_userName)
    EditText etUserName;
    @Bind(R.id.img_clearText1)
    ImageView imgClearText1;
    @Bind(R.id.img_inputCode)
    ImageView imgInputCode;
    @Bind(R.id.et_verificationCode)
    EditText etVerificationCode;
    @Bind(R.id.img_clearText2)
    ImageView imgClearText2;
    @Bind(R.id.btn_getCode)
    Button btnGetCode;
    @Bind(R.id.btn_getCode2)
    Button btnGetCode2;
    @Bind(R.id.tv_countDown)
    TextView tvCountDown;
    @Bind(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void setListener() {
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = etUserName.getText().toString();//得到输入框改变后的文本
                if (!TextUtils.isEmpty(str)) {
                    imgClearText1.setVisibility(View.VISIBLE);
                } else {
                    imgClearText1.setVisibility(View.GONE);
                }

                if (str.length() > 9 && str.length() < 12) {//判断是手机号,显示可点击的按钮
                    btnGetCode.setVisibility(View.VISIBLE);
                    btnGetCode2.setVisibility(View.GONE);
                } else {
                    btnGetCode.setVisibility(View.GONE);
                    btnGetCode2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etVerificationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        imgClearText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etUserName.setText("");
            }
        });
    }

    @Override
    public void showToast(String string) {
        ToastUtils.showToast(this,string);
    }
}
