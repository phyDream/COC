package com.cdlixin.coc.ui.user.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
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
import com.cdlixin.coc.global.MyApplication;
import com.cdlixin.coc.presenter.user.impl.LoginPresenter;
import com.cdlixin.coc.ui.common.widget.TitleBar;
import com.cdlixin.coc.ui.user.view.LoginView;
import com.cdlixin.coc.utils.ClickFilter;
import com.cdlixin.coc.utils.DeviceManager;
import com.cdlixin.coc.utils.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

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
    @Bind(R.id.btn_check)
    Button btnCheck;

    /**
     * 倒计时类
     */
    private CountDownTimer countDownTimer;
    private String verifyCode;
    private String phoneNumber;
    private String simNumber;
    private String deviceId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String defultPhone = presenter.getPhoneNum();
        if(defultPhone != null && !TextUtils.isEmpty(defultPhone)){
            btnGetCode.setVisibility(View.VISIBLE);
            btnGetCode2.setVisibility(View.GONE);
            etUserName.setText(defultPhone);
        }
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void setListener() {

        titleBar.setClickBackListener(new TitleBar.ClickBackListener() {
            @Override
            public void clickBack() {
                onBackPressed();
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String phone = etUserName.getText().toString();
                //获取读取SIM卡序列号权限
                if (!TextUtils.isEmpty(phone)) {
                    AndPermission.with(LoginActivity.this)
                            .requestCode(100)
                            .callback(new PermissionListener() {
                                @Override
                                public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                    if (requestCode == 200) {
                                        btnGetCode.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                    if (requestCode == 200) {
                                        showToast(MyApplication.geResStr(R.string.Failed_to_access_the_phone_card_serial_number));
                                    }
                                }
                            })
                            .permission(Manifest.permission.READ_PHONE_STATE)
                            .start();
                    //手机号验证 - 是否会员
                    presenter.checkPhone(phone);
                }
            }
        });

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
                String str = etVerificationCode.getText().toString();//得到输入框改变后的文本
                if (!TextUtils.isEmpty(str)) {
                    imgClearText2.setVisibility(View.VISIBLE);
                } else {
                    imgClearText2.setVisibility(View.GONE);
                }
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

        imgClearText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etVerificationCode.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(ClickFilter.isFastDoubleClick()){
                    return;
                }
                deviceId = DeviceManager.getPhoneId();
                simNumber = DeviceManager.getSIMNumber();
                phoneNumber = etUserName.getText().toString();
                verifyCode = etVerificationCode.getText().toString();
                if(TextUtils.isEmpty(phoneNumber)){
                    showToast(getResources().getString(R.string.Please_enter_your_phone_number));
                }else if(TextUtils.isEmpty(verifyCode)){
                    showToast(getResources().getString(R.string.Please_fill_in_the_verification_code));
                }else {
                    if(TextUtils.isEmpty(simNumber)){
                        showToast(getResources().getString(R.string.Sim_card_invalid));
                    }else {
                        presenter.deviceRegister(deviceId,simNumber,phoneNumber,verifyCode);
                    }

                }
            }
        });
    }


    @Override
    public void showToast(String string) {
        ToastUtils.showToast(this, string);
    }

    @Override
    public void countDown() {
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (null != btnGetCode) {
                    btnGetCode.setVisibility(View.GONE);
                    btnCheck.setVisibility(View.GONE);
                    imgClearText1.setVisibility(View.GONE);
                    //设置不可编辑
                    etUserName.setFocusable(false);
                    etUserName.setFocusableInTouchMode(false);
                    etUserName.setTextColor(MyApplication.getAppContext().getResources().getColor(R.color.Hint));
                }
                if (tvCountDown != null) {
                    tvCountDown.setVisibility(View.VISIBLE);
                    tvCountDown.setText(millisUntilFinished / 1000 + "S后重新获取");
                }
            }

            public void onFinish() {
                if (null != tvCountDown && null != btnGetCode && etUserName != null) {
                    tvCountDown.setVisibility(View.GONE);
                    btnGetCode.setVisibility(View.VISIBLE);
                    imgClearText1.setVisibility(View.VISIBLE);
                    //设置回可编辑
                    etUserName.setFocusableInTouchMode(true);
                    etUserName.setFocusable(true);
                    etUserName.requestFocus();
                    etUserName.setTextColor(MyApplication.getAppContext().getResources().getColor(R.color.titleTextcolor));
                    etUserName.setEnabled(true);
                }

            }
        };
        countDownTimer.start();
    }

    @Override
    public void checkPhoneNumIng() {
        btnCheck.setVisibility(View.VISIBLE);
        btnGetCode.setVisibility(View.GONE);
    }

    @Override
    public void checkPhoneFaile() {
        btnGetCode.setVisibility(View.VISIBLE);
        btnCheck.setVisibility(View.GONE);
        btnGetCode2.setVisibility(View.GONE);
    }

}
